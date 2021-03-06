import com.jcraft.jsch.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class uploadFileTemp {
    public static void runCommand() {
        try {
            String command = "java -jar /home/ubuntu/PaaS/WorkerEnd.jar";
            String host = DefaultKeys.worker0IP;
            String user = "ubuntu";
            String privateKey = "key.pem";
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            Properties config = new Properties();
            //session.setPassword("KIT318");
            jsch.addIdentity(privateKey);
            System.out.println("identity added ");
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);

            //channel.setInputStream(null);
            //((ChannelExec)channel).setErrStream(System.err);
            channel.setInputStream(System.in);
            channel.connect();

            InputStream input = channel.getInputStream();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(input));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException io) {
                System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
                io.getMessage();

            } catch (Exception e) {
                System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
                e.getMessage();

            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        List<String> MasterSrcs = new ArrayList<String>();
        List<String> MasterDsts = new ArrayList<String>();

        //MasterSrcs.add("key.pem");
        //MasterDsts.add("/home/ubuntu/PaaS/key.pem");

        //MasterSrcs.add("out\\artifacts\\PaaS_jar\\jsch-0.1.55.jar");
        //MasterDsts.add("/home/ubuntu/PaaS/jsch-0.1.55.jar");

       // MasterSrcs.add("out\\artifacts\\PaaS_jar\\openstack4j-3.1.1-20181013.160235-27-withdeps.jar");
        //MasterDsts.add("/home/ubuntu/PaaS/openstack4j-3.1.1-20181013.160235-27-withdeps.jar");

        //MasterSrcs.add("out\\artifacts\\PaaS_jar\\slf4j-api-1.7.24.jar");
        //MasterDsts.add("/home/ubuntu/PaaS/slf4j-api-1.7.24.jar");

        //MasterSrcs.add("out\\artifacts\\PaaS_jar\\slf4j-jdk14-1.7.24.jar");
        //MasterDsts.add("/home/ubuntu/PaaS/slf4j-jdk14-1.7.24.jar");

        MasterSrcs.add("out\\artifacts\\PaaS_jar\\MasterEnd.jar");
        MasterDsts.add("/home/ubuntu/PaaS/MasterEnd.jar");

        //MasterSrcs.add("app.py");
        //MasterDsts.add("/home/ubuntu/PaaS/app.py");
        FileIO.uploadFile(DefaultKeys.masterIP,"key.pem", MasterSrcs,MasterDsts);

        List<String> workerSrcs = new ArrayList<String>();
        List<String> workerDsts = new ArrayList<String>();

       /* workerSrcs.add("out\\production\\PaaS\\FileIO.class");
        workerDsts.add("/home/ubuntu/PaaS/FileIO.class");

        workerSrcs.add("out\\production\\PaaS\\DefaultKeys.class");
        workerDsts.add("/home/ubuntu/PaaS/DefaultKeys.class");

        workerSrcs.add("out\\production\\PaaS\\Storage.class");
        workerDsts.add("/home/ubuntu/PaaS/Storage.class");

        workerSrcs.add("out\\production\\PaaS\\Job.class");
        workerDsts.add("/home/ubuntu/PaaS/Job.class");

        workerSrcs.add("out\\production\\PaaS\\WorkBook.class");
        workerDsts.add("/home/ubuntu/PaaS/WorkBook.class");

        workerSrcs.add("out\\production\\PaaS\\WorkerEnd.class");
        workerDsts.add("/home/ubuntu/PaaS/WorkerEnd.class");

        workerSrcs.add("out\\production\\PaaS\\Worker.class");
        workerDsts.add("/home/ubuntu/PaaS/Worker.class");

        workerSrcs.add("out\\production\\PaaS\\Secretary.class");
        workerDsts.add("/home/ubuntu/PaaS/Secretary.class");

        workerSrcs.add("out\\production\\PaaS\\ServerOneSecretary.class");
        workerDsts.add("/home/ubuntu/PaaS/ServerOneSecretary.class");

        workerSrcs.add("out\\production\\PaaS\\ServerOneWorker.class");
        workerDsts.add("/home/ubuntu/PaaS/ServerOneWorker.class");

        workerSrcs.add("key.pem");
        workerDsts.add("/home/ubuntu/PaaS/key.pem");

        workerSrcs.add("jsch-0.1.55.jar");
        workerDsts.add("/home/ubuntu/PaaS/jsch-0.1.55.jar");*/

        workerSrcs.add("key.pem");
        workerDsts.add("/home/ubuntu/PaaS/key.pem");

        workerSrcs.add("out\\artifacts\\PaaS_jar\\jsch-0.1.55.jar");
        workerDsts.add("/home/ubuntu/PaaS/jsch-0.1.55.jar");

        workerSrcs.add("out\\artifacts\\PaaS_jar\\openstack4j-3.1.1-20181013.160235-27-withdeps.jar");
        workerDsts.add("/home/ubuntu/PaaS/openstack4j-3.1.1-20181013.160235-27-withdeps.jar");

        workerSrcs.add("out\\artifacts\\PaaS_jar\\slf4j-api-1.7.24.jar");
        workerDsts.add("/home/ubuntu/PaaS/slf4j-api-1.7.24.jar");

        workerSrcs.add("out\\artifacts\\PaaS_jar\\slf4j-jdk14-1.7.24.jar");
        workerDsts.add("/home/ubuntu/PaaS/slf4j-jdk14-1.7.24.jar");

        workerSrcs.add("out\\artifacts\\PaaS_jar\\WorkerEnd.jar");
        workerDsts.add("/home/ubuntu/PaaS/WorkerEnd.jar");


        //FileIO.uploadFile(DefaultKeys.worker0IP,"key.pem", workerSrcs,workerDsts);
        //FileIO.uploadFile(DefaultKeys.worker2IP,"key.pem", workerSrcs,workerDsts);

        List<String> WorkerSrcs = new ArrayList<String>();
        List<String> WorkerDsts = new ArrayList<String>();

        WorkerSrcs.add("/home/ubuntu/app.py");
        WorkerDsts.add("add.py");

        //FileIO.downloadFile(DefaultKeys.worker1IP,"key.pem" ,WorkerSrcs, WorkerDsts);

        //runCommand();

       /* List<String> cmdList = Arrays.asList("java -jar", "/home/ubuntu/PaaS/WorkDir/ZLsCXyc9/app.jar", "/home/ubuntu/PaaS/WorkDir/ZLsCXyc9/input.txt");
        System.out.println(cmdList);
        System.out.println("Creating Process");

        ProcessBuilder builder = new ProcessBuilder(cmdList);

        builder.redirectErrorStream(true);// 重定向错误输出流到正常输出流

        File dir = new File("/home/ubuntu/PaaS/WorkDir/ZLsCXyc9/");

        //builder.directory(dir);
        Process currentProcess = null;
        try {
            //String command = cmdList.get(0) + " " +

            currentProcess =  Runtime.getRuntime().exec("python /home/ubuntu/PaaS/WorkDir/48isvCMa/app.py /home/ubuntu/PaaS/WorkDir/48isvCMa/input.txt", null, dir);

            InputStream is = currentProcess.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            currentProcess.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }
}
