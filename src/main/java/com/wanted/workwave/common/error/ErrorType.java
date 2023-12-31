package com.wanted.workwave.common.error;

import com.wanted.workwave.common.jwt.exception.ExpiredTokenException;
import com.wanted.workwave.common.jwt.exception.MissingRequestHeaderAuthorizationException;
import com.wanted.workwave.team.exception.AlreadyApprovedInviteException;
import com.wanted.workwave.team.exception.InvalidInviteAccessException;
import com.wanted.workwave.team.exception.NotFoundTeamInviteException;
import com.wanted.workwave.team.exception.NotTeamLeaderException;
import com.wanted.workwave.user.exception.DuplicateUsernameException;
import com.wanted.workwave.user.exception.MismatchedPasswordException;
import com.wanted.workwave.user.exception.NotFoundUsernameException;
import com.wanted.workwave.column.exception.*;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    U001("U001", "중복된 계정입니다.", DuplicateUsernameException.class, HttpStatus.CONFLICT),
    U002("U002", "존재하지 않는 계정입니다.", NotFoundUsernameException.class, HttpStatus.NOT_FOUND),
    U003("U003", "비밀번호가 일치하지 않습니다.", MismatchedPasswordException.class, HttpStatus.NOT_FOUND),

    T001("T001", "헤더에 토큰이 존재하지 않습니다.", MissingRequestHeaderAuthorizationException.class, HttpStatus.UNAUTHORIZED),
    T002("T002", "만료된 토큰입니다.", ExpiredTokenException.class, HttpStatus.UNAUTHORIZED),

    I001("I001", "팀장만 팀월을 초대할 수 있습니다.", NotTeamLeaderException.class, HttpStatus.NOT_FOUND),
    I002("I002", "존재하지 않는 초대입니다.", NotFoundTeamInviteException.class, HttpStatus.NOT_FOUND),
    I003("I003", "초대를 승낙할 수 없습니다.", InvalidInviteAccessException.class, HttpStatus.FORBIDDEN),
    I004("I004", "이미 승인된 초대입니다.", AlreadyApprovedInviteException.class, HttpStatus.CONFLICT),

    W001("W001", "로그인한 유저가 해당 팀 멤버가 아닙니다.", NotLoggedInUserTeamMemberException.class, HttpStatus.FORBIDDEN),
    W002("W002", "존재하지 않는 워크플로우 입니다.", NotFoundColumnException.class, HttpStatus.NOT_FOUND),
    W003("W003", "해당 팀의 워크플로우가 아닙니다.", MismatchedTeamColumnException.class, HttpStatus.NOT_FOUND),
    W004("W004", "작업이 있는 워크플로우는 삭제할 수 없습니다.", ColumnHasTicketsException.class, HttpStatus.CONFLICT),
    W005("W005", "잘못된 이동할 위치입니다.", InvalidPositionException.class, HttpStatus.BAD_REQUEST),
    W006("W006", "올바르지 않은 작업 태그가 사용되었습니다.", InvalidTagException.class, HttpStatus.BAD_REQUEST),
    W007("W007", "존재하지 않는 작업 입니다.", NotFoundTicketException.class, HttpStatus.NOT_FOUND),
    W008("W008", "할당하려는 유저가 팀 멤버가 아닙니다.", AssigneeNotTeamMemberException.class, HttpStatus.FORBIDDEN);

    private final String code;
    private final String message;
    private final Class<? extends CustomException> classType;
    private final HttpStatus httpStatus;
    private static final List<ErrorType> errorTypes = Arrays.stream(ErrorType.values()).toList();

    public static ErrorType of(Class<? extends CustomException> classType) {
        return errorTypes.stream()
                .filter(it -> it.classType.equals(classType))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
