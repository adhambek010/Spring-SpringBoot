package uz.developer.magistratura_ishi.service.base;

import uz.developer.magistratura_ishi.model.response.base.ApiResponse;

public interface BaseService {

    ApiResponse SUCCESS = new ApiResponse("muvafaqiyatli bajarildi",true,0);
    ApiResponse ERROR = new ApiResponse("amal bajarilmadi",false,-1);
    ApiResponse SUCCESS_V2 = new ApiResponse("muvafaqiyatli bajarildi",true,0);
    ApiResponse USER_EXIST = new ApiResponse("bu username allaqachon mavjud",false,-100);
    ApiResponse USER_NOT_FOUND = new ApiResponse("bu user topilmadi",false,-101);
    ApiResponse ERROR_USERNAME_OR_PASSWORD = new ApiResponse("Username yoki password xato",false,-102);
    ApiResponse ERROR_FILE_CREATED = new ApiResponse("Fayl yaratishda xatolik",false,-103);
    ApiResponse THEME_NOT_FOUND = new ApiResponse("bu tema topilmadi",false,-104);
    ApiResponse PLAN_TOPIC_NOT_FOUND = new ApiResponse("bu planTopic topilmadi",false,-105);
    ApiResponse STUDENT_NOT_FOUND = new ApiResponse("bu Student topilmadi",false,-112);
    ApiResponse INVALID_CONFIRM_COD = new ApiResponse("Tasdiqlash ko'di xato",false,-106);
    ApiResponse PLAN_TOPIC_ALREADY_EXIST = new ApiResponse("Bu PlanTopic allaqachon mavjud",false,-107);
    ApiResponse BOB_ALREADY_EXIST = new ApiResponse("Bu Bob allaqachon mavjud",false,-113);
    ApiResponse MAQOLA_ALREADY_EXIST = new ApiResponse("Bu Maqola allaqachon mavjud",false,-115);
    ApiResponse CANNOT_BE_CHANGED = new ApiResponse("O'zgartirib bo'lmaydi",false,-108);
    ApiResponse ALREADY_CONFIRM = new ApiResponse("Bu PlanTopic allaqachon tasdiqlangan",false,-109);
    ApiResponse NOT_DELETED = new ApiResponse("Bu plantopicni o'chirib bo'lmaydi, Allaqachon tasdiqlangan",false,-110);
    ApiResponse CALENDAR_TOPIC_NOT_FOUND = new ApiResponse("Bu Calendar reja topilmadi",false,-111);
    ApiResponse NOT_FOUND = new ApiResponse("NOT FOUND",false,-2);

}
