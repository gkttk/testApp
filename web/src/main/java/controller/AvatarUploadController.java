package controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.testApp.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping
public class AvatarUploadController {

    private String rootPath = "D:\\Projects\\userAvatars\\";    /*"C:\\Users\\Кирилл\\image\\";*/
    private File defaultAvatar = new File("D:\\Projects\\userAvatars\\defaultAvatar.jpg");

    @PostMapping("/uploadImage")
    public String uploadAvatar(@RequestParam(value = "avatar") MultipartFile image) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String fileName = authUser.getLogin() + authUser.getEmail();
        processImage(image, fileName);
        return "redirect:/user";
    }


    @GetMapping(value = "/downloadImage")
    public ResponseEntity<byte[]> downloadAvatar(@RequestParam(value = "name") String fileName) {
        File file = new File(rootPath + fileName);
        byte[] content = new byte[0];
       try {
           if (file.exists()) {
               content = Files.readAllBytes(file.toPath());
           } else {
               content = Files.readAllBytes(defaultAvatar.toPath());
           }
       }catch (IOException ex) {
            //Exception handling
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(content.length);
        return new ResponseEntity<>(content,headers,HttpStatus.OK);

}

    private void processImage(MultipartFile image, String fileName) {
        try {
            if (image != null && !image.isEmpty()) {
                validateImage(image);
                saveImage(fileName, image);
            }
        } catch (IOException e) {
            //Error handling
        }
    }


    private void validateImage(MultipartFile image) throws IOException {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new IOException("Only JPG images accepted");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws IOException {
        File file = new File(rootPath + filename + ".jpg");
        Files.write(file.toPath(), image.getBytes());
    }

}
