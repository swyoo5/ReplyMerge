package org.lsh.teamthreeproject.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String uuid;
    private String fileName;
    private boolean image;
    public String getLink(){
        if (image){
            return "s_"+uuid+"_"+fileName;
        }else {
            return uuid+"_"+fileName;
        }
    }
}
