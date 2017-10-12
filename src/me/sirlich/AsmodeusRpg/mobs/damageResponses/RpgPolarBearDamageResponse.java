package me.sirlich.AsmodeusRpg.mobs.damageResponses;
import me.sirlich.AsmodeusRpg.core.RpgEntityList;

import java.util.UUID;

public class RpgPolarBearDamageResponse extends DamageResponse
{
    UUID uuid;
    public RpgPolarBearDamageResponse(UUID uuid){
        this.uuid = uuid;
    }

    @Override
    public void run(){
        RpgEntityList.getRpgEntity(uuid).setAggression(true);
    }
}
