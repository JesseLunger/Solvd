package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public enum BusinessType {
        soleProprietorship("SoleProprietorship"),
        partnership("Partnership"),
        corporation("Corporation"),
        sCorporation("SCorporation");

        private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

        private final String businessType;

        BusinessType(String businessType) {
            this.businessType = businessType;
        }

        static {
                LOGGER.info("Initializing BusinessType enum");
        }

        public String getBusinessType() {
            return businessType;
        }
}
