package com.michal.mqtt.api;

import com.michal.mqtt.api.model.request.CreateUserModel;
import com.michal.mqtt.api.model.request.LoginUserModel;
import com.michal.mqtt.api.model.response.LoginResponseModel;
import com.michal.mqtt.api.model.response.SignResponseModel;
import com.michal.mqtt.api.utils.RestCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("/auth")
public class AuthApi {

    private RestCallService restCallService;

    public AuthApi(RestCallService restCallService) {
        this.restCallService = restCallService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public ResponseEntity<Serializable> login(@Valid @RequestBody LoginUserModel loginUserModel) {
        return restCallService.postRestAuthByUrl("login", loginUserModel, LoginResponseModel.class);
    }

    @RequestMapping(value = "sign", method = RequestMethod.POST)
    public ResponseEntity<Serializable> sign(@RequestBody @Valid CreateUserModel createUserModel) {
        return restCallService.postRestAuthByUrl("login/sign", createUserModel, SignResponseModel.class);
    }

    /*@RequestMapping(value = "verify", method = RequestMethod.POST)
    public ResponseEntity<VerifyUserResponseModel> verify(@RequestBody @Valid VerifyUserModel verifyUserModel) {
        RestTemplate restTemplate = new RestTemplate();
        String url = env.getProperty("auth.url") + "auth/verify";
        return restTemplate.postForEntity(url, verifyUserModel, VerifyUserResponseModel.class);
    }*/
}
