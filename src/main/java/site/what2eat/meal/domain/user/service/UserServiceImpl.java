package site.what2eat.meal.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import site.what2eat.meal.domain.user.converter.UserConverter;
import site.what2eat.meal.domain.user.dto.request.UserReqDto;
import site.what2eat.meal.domain.user.entity.User;
import site.what2eat.meal.domain.user.exception.UserErrorCode;
import site.what2eat.meal.domain.user.exception.UserException;
import site.what2eat.meal.domain.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void addUser(UserReqDto.UserCreateReqDto userCreateReqDto) {
        User user = UserConverter.toUser(userCreateReqDto);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserException(UserErrorCode.USER_CONFLICT);
        }

    }
}
