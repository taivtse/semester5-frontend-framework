package com.connectnow.api;

import com.connectnow.constant.ApiConstant;
import com.connectnow.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping(value = ApiConstant.API_MEMBER_URL)
public class MemberApi {

    Logger logger = LoggerFactory.getLogger(MemberApi.class);

    private final MemberService memberService;

    @Autowired
    public MemberApi(MemberService memberService) {
        this.memberService = memberService;
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity updateReadStatus(@RequestBody Map<String, Object> data) {
        try {
            BigInteger chatBoxId = new BigInteger(data.get("chatBoxId").toString());
            BigInteger userId = new BigInteger(data.get("userId").toString());
            boolean readStatus = (boolean) data.get("readStatus");
            this.memberService.updateReadStatusByChatBoxIdAndUserId(chatBoxId, userId, readStatus);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(null);
    }
}
