package de.devdanmu.wahlblockbackend.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author danmu
 * created on 2018-03-20
 */
@Data
public class VoterLogin {

    @NotBlank(message = "The voter key can't be empty")
    @Pattern(regexp = "^[0-9CFGHJKLMNPRTVWXYZ]+$", message = "The id card number is formatted incorrectly.")
    private String idCardNumber;

    @NotBlank(message = "The voter key can't be empty.")
    @Pattern(regexp = "^[0-9]{8}$", message = "The voter key is formatted incorrectly.")
    private String voterKey;

    @NotBlank(message = "The public key can't be empty.")
    private String publicKey;

}
