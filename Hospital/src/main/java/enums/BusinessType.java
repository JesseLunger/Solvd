package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public enum BusinessType {
        SOLE_PROPRIETORSHIP("SoleProprietorship"),
        PARTNERSHIP("Partnership"),
        CORPORATION("Corporation"),
        S_CORPORATION("SCorporation");

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
