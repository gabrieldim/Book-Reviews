//package mk.ukim.finki.booksreviews.service.impl;
//
//import lombok.AllArgsConstructor;
//import mk.ukim.finki.booksreviews.model.entity.User;
//import mk.ukim.finki.booksreviews.model.request.UserRequest;
//import mk.ukim.finki.booksreviews.repository.UserRepository;
//import mk.ukim.finki.booksreviews.service.UserService;
//import mk.ukim.finki.booksreviews.util.ValidationUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//@Transactional
//@AllArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public Optional<User> loginUser(UserRequest userRequest) {
//        return userRepository.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
//    }
//
//    @Override
//    public Optional<User> registerUser(UserRequest userRequest) {
//        if (!ValidationUtils.isValidUser(userRequest.getEmail(), userRequest.getPassword())) {
//            return Optional.empty();
//        }
//        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
//            return Optional.empty();
//        }
//        User author = User.of(userRequest.getFirstName(), userRequest.getLastName(),
//                userRequest.getEmail(), userRequest.getPassword(), userRequest.getRole());
//
//        return Optional.of(userRepository.save(author));
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
//    }
//}
