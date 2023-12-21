package com.example.javaproject.services;

import com.example.javaproject.entity.NotificationEntity;
import com.example.javaproject.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationEntity> recupereToutesLesNotifications(){
        return (List<NotificationEntity>) notificationRepository.findAll();
    }

    public NotificationEntity recupereNotification(Long id){
        return notificationRepository.findById(id).orElse(null);
    }
    public void ajouterNotification(NotificationEntity notification){
        notificationRepository.save(notification);
    }

    public void supprimerNotification(Long id){
        notificationRepository.deleteById(id);
    }
}
