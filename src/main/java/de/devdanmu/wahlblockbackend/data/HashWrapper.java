package de.devdanmu.wahlblockbackend.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author danmu
 * created on 2018-03-20
 */
@Data
public class HashWrapper {

    @JsonProperty("data")
    private List<String> hashList;
}
