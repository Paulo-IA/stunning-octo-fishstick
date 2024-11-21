package com.taskflow.api.services;

import com.amazonaws.services.s3.AmazonS3;
import com.taskflow.api.domain.user.User;
import com.taskflow.api.domain.user.UserRequestDTO;
import com.taskflow.api.domain.user.UserResponseDTO;
import com.taskflow.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AmazonS3 s3Client;

    public User createUser(UserRequestDTO data) {
        String imgUrl = null;

        if (data.image() != null) {
            imgUrl = this.uploadImg(data.image());
        }

        User newUser = new User();

        newUser.setName(data.name());
        newUser.setEmail(data.email());
        newUser.setImgUrl(imgUrl);

        this.userRepository.save(newUser);

        return newUser;
    }

    public List<UserResponseDTO> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = this.userRepository.findAll(pageable);

        return usersPage.map(user -> new UserResponseDTO(
                user.id,
                user.name,
                user.imgUrl
        )).stream().toList();
    }

    public User findUserById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User doesn't exists"));
    }

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getName();

        try {
            File file = this.convertMultipartToFile(multipartFile);
            s3Client.putObject(bucketName, filename, file);
            file.delete();
            return s3Client.getUrl(bucketName, filename).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error while uploading file!");
            return null;
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

}
