package com.michellecabrerac.mysecretsanta;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import com.michellecabrerac.mysecretsanta.exception.BusinessRuleException;
import com.michellecabrerac.mysecretsanta.exception.DuplicateEmailException;
import com.michellecabrerac.mysecretsanta.exception.UserNotFoundException;
import com.michellecabrerac.mysecretsanta.model.User;
import com.michellecabrerac.mysecretsanta.repository.UserRepository;
import com.michellecabrerac.mysecretsanta.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.*;

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
Arrange (Preparación): Prepare the test environment.
Initialize objects, create test data, set up variables, and configure dependencies.
Example: Creating a user account, loading a list of items.

Act (Acción): Perform the action you want to test.
Call the method or function you are testing (the System Under Test or SUT).
Example: Submitting a form, calling a calculation function, logging in.

Assert (Verificación): Verify the outcome.
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

    @Test
    @DisplayName("Should throw a exception when id of user does not exist")
    void shouldThrowUserNotFoundException_whenTheUserIdIsNotFound(){
        Long nonExistingId = -1L;
        when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> userService.getUserById(nonExistingId))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository).findById(nonExistingId);
    }
    @Test
    @DisplayName("Should throw a UserNotFoundException when user email does not exist")
    void shouldThrowUserNotFoundException_whenEmailNotFound(){
        String nonExistingEmail = "nonExistingEmail@mail.com";
        when(userRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> userService.getUserByEmail(nonExistingEmail))
                .isInstanceOf(UserNotFoundException.class);
        verify(userRepository, atLeastOnce()).findByEmail(nonExistingEmail);
    }
    /*Test creación*/
    @Test
    @DisplayName("Should return a created user when data entered is valid")
    void shouldReturnCreatedUser_whenDataIsValid(){ //should_<expected>_when_<condition>
        testUser = User.builder()
                .id(1L)
                .email(VALID_EMAIL)
                .name(NAME)
                .surname(SURNAME)
                .createdAt(LocalDateTime.now()).build();
        //Arrange
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).then(AdditionalAnswers.returnsFirstArg());
        //Act
        User result = userService.createUser(VALID_EMAIL, NAME, SURNAME);
        //Assert
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertThat(result).isNotNull();

        //Validación agrupada -> reporta todos los fallos a la vez
        assertThat(capturedUser).satisfies( user -> {
                    assertThat(user.getEmail()).isEqualTo(VALID_EMAIL);
                    assertThat(user.getName()).isEqualTo(NAME);
                    assertThat(user.getSurname()).isEqualTo(SURNAME);
        });
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
   // @ValueSource(strings = {"email.com", "@gmail.com", "email@email@.com"})
    @DisplayName("Should throw a BusinessRuleException when the email is invalid")
    void shouldThrowBusinessRuleException_whenEmailIsNullOrEmpty(String invalidEmail){
        assertThatThrownBy(()-> userService.createUser(invalidEmail, NAME, SURNAME))
                .isInstanceOf(BusinessRuleException.class);
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
    @DisplayName("Should update user fields correctly based on input values")
    void shouldUpdateUser_whenDataIsValid(){
        Long userId = 1L;

        testUser = User.builder()
                        .id(userId)
                        .email(VALID_EMAIL)
                        .name(NAME)
                        .surname(SURNAME)
                        .createdAt(LocalDateTime.now()).build();

        String updatedName = "Updated Name";
        String updatedSurname = "Updated Surname";
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        //Mockito devuelve lo mismo que le acaban de pasar
        when(userRepository.save(any(User.class))).then(AdditionalAnswers.returnsFirstArg());

        User result = userService.updateUser(userId, updatedName, updatedSurname);

        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertThat(result).isNotNull();
        assertThat(capturedUser).satisfies(user -> {
            assertThat(user.getId()).isEqualTo(userId);
            assertThat(user.getName()).isEqualTo(updatedName);
            assertThat(user.getSurname()).isEqualTo(updatedSurname);
        });

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
