package me.sirlich.AsmodeusRpg.customMobs.damageResponses;

import me.sirlich.AsmodeusRpg.core.RpgEntityList;

import java.util.UUID;

public class RpgCowDamageResponse extends DamageResponse
{
    UUID uuid;
    public RpgCowDamageResponse(UUID uuid){
        this.uuid = uuid;
    }

    @Override
    public void run(){
        RpgEntityList.getRpgEntity(uuid).setAggression(true);
    }
}