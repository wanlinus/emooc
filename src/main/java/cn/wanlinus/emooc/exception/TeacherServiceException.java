package cn.wanlinus.emooc.exception;

/**
 * 教师服务异常
 *
 * @author wanli
 * @date 2018-03-11 00:47
 */
public class TeacherServiceException extends RuntimeException {
    public TeacherServiceException(String message) {
        super(message);
        System.err.println(message);
    }
}
