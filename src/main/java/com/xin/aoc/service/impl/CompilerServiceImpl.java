package com.xin.aoc.service.impl;

import com.xin.aoc.service.CompilerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class CompilerServiceImpl implements CompilerService {
    static Logger logger = LoggerFactory.getLogger(CompilerServiceImpl.class);
    private String osType = "linux";
    private String compileCmd = "";
    private String executeCmd = "";
    private String compileDir = "";
    private String execSuffix = "";
    private String sysCharset = "UTF-8";

    public CompilerServiceImpl() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("win")) {
            osType = "win";
            execSuffix = ".exe";
            sysCharset = "GBK";
        } else if (os.startsWith("mac")) {
            osType = "mac";
        } else {
            osType = "linux";
        }

        try {
            Properties properties = new Properties();
            Properties prop = new Properties();
            prop.load(CompilerServiceImpl.class.getResourceAsStream("/compiler.properties"));
            compileCmd = prop.getProperty(osType + ".compile", "");
            executeCmd = prop.getProperty(osType + ".execute", "");
            logger.info("compileCmd:" + compileCmd);
            logger.info("executeCmd:" + executeCmd);
        } catch (Exception e) {
            logger.error("Error", e);
        }

        try {
            File file = new File("complie");
            if (file.exists() == false)
                file.mkdir();
            compileDir = file.getAbsolutePath();
            logger.info("compileDir:" + compileDir);
        } catch (Exception e) {
            logger.error("Error", e);
        }
    }

    private String callCmd(String command) {
        try {
            List<String> cmd = new ArrayList<String>();
            if (osType.equals("win")) {
                cmd.add("cmd");
                cmd.add("/c");
            } else {
                cmd.add("sh");
                cmd.add("-c");
            }
            cmd.add(command);
            logger.info("command:" + command);
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            return new String(process.getInputStream().readAllBytes(), sysCharset);
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return "callCmd error.";
    }

    private String generateCmd(String cmd, Properties properties) {
        String ret = cmd;
        for (Object k : properties.keySet()) {
            String key = (String) k;
            String value = properties.getProperty(key);
            ret = ret.replace("${" + key + "}", value);
        }
        return ret;
    }

    @Override
    public String compile(String name, String codeText) {
        if (compileCmd.length() == 0 || compileDir.length() == 0)
            return "compile error1.";

        try {
            File dir = new File(compileDir, name);
            if (dir.exists() == false)
                dir.mkdir();

            File src = new File(dir, name + ".cpp");
            File dst = new File(dir, name + execSuffix);
            Files.write(src.toPath(), codeText.getBytes());

            Properties prop = new Properties();
            prop.setProperty("compile_dir", dir.getAbsolutePath());
            prop.setProperty("src_file", src.getAbsolutePath());
            prop.setProperty("exe_file", dst.getAbsolutePath());
            String command = generateCmd(compileCmd, prop);
            return callCmd(command);
        } catch (Exception e) {
            logger.error("Error", e);
        }

        return "compile error2.";
    }

    @Override
    public String execute(String name, String input) {
        if (executeCmd.length() == 0 || compileDir.length() == 0)
            return "execute error1.";

        try {
            File dir = new File(compileDir, name);
            if (dir.exists() == false)
                dir.mkdir();

            File inFile = new File(dir, name + ".txt");
            File execFile = new File(dir, name + execSuffix);
            Files.write(inFile.toPath(), input.getBytes());

            Properties prop = new Properties();
            prop.setProperty("compile_dir", dir.getAbsolutePath());
            prop.setProperty("input_file", inFile.getAbsolutePath());
            prop.setProperty("exe_file", execFile.getAbsolutePath());
            String command = generateCmd(executeCmd, prop);
            return callCmd(command);
        } catch (Exception e) {
            logger.error("Error", e);
        }

        return "execute error2.";
    }
}
