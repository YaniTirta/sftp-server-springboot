package com.sftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SftpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SftpApplication.class, args);
	}

//    @PostConstruct
//    public void startSftpServer() throws IOException {
//        SshServer sshd = SshServer.setUpDefaultServer();
//        sshd.setPort(2222); // Port untuk SFTP
//
//        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get("hostkey.ser")));
//        sshd.setPasswordAuthenticator(simplePasswordAuthenticator());
//
//        // Atur folder dasar untuk user
//        Path userHome = Paths.get("c:/Temp/swift").toAbsolutePath();
//        sshd.setFileSystemFactory(new VirtualFileSystemFactory(userHome));
//
//        // Aktifkan subsystem SFTP
//        sshd.setSubsystemFactories(Collections.singletonList(new SftpSubsystemFactory()));
//
//        sshd.start();
//        System.out.println("✅ SFTP Server started on port 2222, root: " + userHome);
//    }
//    
//    private PasswordAuthenticator simplePasswordAuthenticator() {
//        return (username, password, session) -> {
//            // Username dan password statis untuk MVP
//            return "user1".equals(username) && "pass123".equals(password);
//        };
//    }
}
