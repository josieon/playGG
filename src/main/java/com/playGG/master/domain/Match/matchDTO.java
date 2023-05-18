package com.playGG.master.domain.Match;

public class matchDTO {
    metadataDTO metadata;
    public infoDTO info;

    public infoDTO getInfo() {
        return info;
    }

    public matchDTO() {
    }

    public void setMetadata(metadataDTO metadata) {
        this.metadata = metadata;
    }

    public void setInfo(infoDTO info) {
        this.info = info;
    }
}