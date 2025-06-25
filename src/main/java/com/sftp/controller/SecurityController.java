package com.sftp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @Value("${app.text-file-directory:./text-files}")
    private String textFileDirectory;
    
    // Menampilkan halaman home
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("message", "Selamat Datang di Aplikasi Aman!");
        return "home"; // Mengarahkan ke src/main/resources/templates/home.html
    }

    // Menampilkan halaman login kustom
    @GetMapping({"/", "/login", "/logout"})
    public String loginPage() {
        return "login"; // Mengarahkan ke src/main/resources/templates/login.html
    }

    // Menampilkan halaman yang dilindungi (hanya untuk pengguna terotentikasi)
    @GetMapping("/secured")
    public String securedPage(Model model) {
        model.addAttribute("message", "Ini adalah halaman yang dilindungi. Hanya pengguna terotentikasi yang bisa melihat ini.");
        return "secured"; // Mengarahkan ke src/main/resources/templates/secured.html
    }
    
    @GetMapping("/error")
    public String errorPage(Model model) {
        model.addAttribute("files", "");
        model.addAttribute("errorMessage", "Ada kesalahan umum");
        return "error";
    }

}