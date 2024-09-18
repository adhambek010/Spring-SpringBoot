package uz.developer.magistratura_ishi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignInReceiveModel {

   private String username;
   private String password;
}
