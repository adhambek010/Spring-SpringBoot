package uz.developer.magistratura_ishi.model.response;

import lombok.Data;

import java.util.List;

@Data
public class StudentTopicInfoResponse {
    private String theme;
    private List<StudentTopicListResponse> topicList;
    private UserResponseModel student;

}
