package de.devdanmu.wahlblockbackend.data;

import lombok.Data;

/**
 * @author danmu
 * created on 2018-03-20
 */
@Data
public class LoginVoter {

    private String idCardNumber;

    private Integer voterKey;

    private String publicKey;

}
