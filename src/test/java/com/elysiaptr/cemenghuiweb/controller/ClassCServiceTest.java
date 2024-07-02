package com.elysiaptr.cemenghuiweb.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.ClassC;
import com.elysiaptr.cemenghuiweb.web.repo.ClassCRepository;
import com.elysiaptr.cemenghuiweb.web.service.impl.ClassCServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClassCServiceTest {

    @Mock
    private ClassCRepository classCRepository;

    @InjectMocks
    private ClassCServiceImpl classCServiceImpl;

    @Test
    void testDeleteClassCSuccess() {
        Long classCId = 1L;
        ClassC classC = new ClassC();
        classC.setId(classCId);

        when(classCRepository.findById(classCId)).thenReturn(Optional.of(classC));

        classCServiceImpl.deleteClassC(classCId);

        verify(classCRepository, times(1)).findById(classCId);
        verify(classCRepository, times(1)).delete(classC);
    }

    @Test
    void testDeleteClassCNotFound() {
        Long classCId = 2L;

        when(classCRepository.findById(classCId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            classCServiceImpl.deleteClassC(classCId);
        });

        String expectedMessage = "ClassC not found for this id :: " + classCId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(classCRepository, times(1)).findById(classCId);
        verify(classCRepository, times(0)).delete(any(ClassC.class));
    }
}

