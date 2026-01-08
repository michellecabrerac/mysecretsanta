package com.michellecabrerac.mysecretsanta;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import com.michellecabrerac.mysecretsanta.exception.BusinessRuleException;
import com.michellecabrerac.mysecretsanta.exception.DuplicateEmailException;
import com.michellecabrerac.mysecretsanta.exception.UserNotFoundException;
import com.michellecabrerac.mysecretsanta.model.User;
import com.michellecabrerac.mysecretsanta.repository.UserRepository;
import com.michellecabrerac.mysecretsanta.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

/*The AAA Pattern Explained
Arrange: Prepare the test environment.
Initialize objects, create test data, set up variables, and configure dependencies.
Example: Creating a user account, loading a list of items.

Act: Perform the action you want to test.
Call the method or function you are testing (the System Under Test or SUT).
Example: Submitting a form, calling a calculation function, logging in.

Assert: Verify the outcome.
Check if the actual result matches the expected result using assertion methods.
Example: Confirming a status code is 'OK', checking if an item appears in a list, verifying a calculation is correct.
* */
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    /*ArgumentCaptor nos permite capturar un argumento pasado a un método para inspeccionarlo*/
    @Captor
    private ArgumentCaptor<User> userCaptor;

    private User testUser;
    private static final String VALID_EMAIL = "test@gmail.com";
    private static final String NAME = "Test Name";
    private static final String SURNAME = "Test Surname";

    @BeforeEach
    void setUp(){
        testUser = User.builder()
                .id(1L)
                .email(VALID_EMAIL)
                .name(NAME)
                .surname(SURNAME)
                .createdAt(LocalDateTime.now()).build();
    }
    /*Test creación*/
    @Test
    @DisplayName("Should return a created user when data entered is valid")
    void shouldReturnCreatedUser_whenDataIsValid(){ //should_<expected>_when_<condition>
        //Arrange
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        //Act
        User result = userService.createUser(VALID_EMAIL, NAME, SURNAME);
        //Assert
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(VALID_EMAIL);
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getSurname()).isEqualTo(SURNAME);

        verify(userRepository).save(any(User.class));

    }
    @Test
    @DisplayName("Should throw a DuplicatedEmailException when the email already exists")
    void shouldThrowDuplicatedEmailException_whenEmailAlreadyExists(){
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThatThrownBy(()-> userService.createUser(VALID_EMAIL, NAME, SURNAME))
                .isInstanceOf(DuplicateEmailException.class);

        verify(userRepository, never()).save(any(User.class));
    }
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw a BusinessRuleException when the email is null or empty")
    void shouldThrowBusinessRuleException_whenEmailIsNullOrEmpty(String invalidEmail){
        assertThatThrownBy(()-> userService.createUser(invalidEmail, NAME, SURNAME))
                .isInstanceOf(BusinessRuleException.class);
        //verify(userRepository, never()).save(any(User.class));
        verifyNoInteractions(userRepository);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw a BusinessRuleException when the name is invalid")
    void shouldThrowBusinessRuleException_whenNameIsInvalid(String invalidName){
        assertThatThrownBy(()-> userService.createUser(VALID_EMAIL, invalidName, SURNAME))
                .isInstanceOf(BusinessRuleException.class);
        verifyNoInteractions(userRepository);
    }
    @Test
    @DisplayName("Should update username and surname specifically")
    void shouldUpdateUser_whenDataIsValid(){
        Long userId = testUser.getId();
        String updatedName = "Updated Name";
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        //Mockito devuelve lo mismo que le acaban de pasar
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User result = userService.updateUser(userId, updatedName, null);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertThat(savedUser.getId()).isEqualTo(userId);
        assertThat(savedUser.getName()).isEqualTo(updatedName);
        assertThat(savedUser.getSurname()).isNull();
    }
    @Test
    @DisplayName("Should return UserNotFoundException when the user doesn't exist on delete")
    void shouldThrowUserNotFoundException_whenDeletingNonExitingUser(){
        Long inventedId = 999999L;
        when(userRepository.findById(inventedId)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> userService.deleteUser(inventedId)).isInstanceOf(UserNotFoundException.class);
        verify(userRepository, never()).delete(any());
    }
}
