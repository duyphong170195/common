package com.eight_seneca.common.util;

import org.springframework.util.StringUtils;

public class SqlUtil {
   private static String PERCENT = "%";

   public SqlUtil() {
   }

   public static String fillSearchLike(String input) {
      if (StringUtils.hasLength(input)) {
         String var10000 = PERCENT;
         return var10000 + input.trim().replaceAll("%", "\\\\%") + PERCENT;
      } else {
         return null;
      }
   }

   public static String fillSearchLikeIncludeEmpty(String input) {
      if (StringUtils.hasLength(input)) {
         return PERCENT + PERCENT;
      } else {
         String var10000 = PERCENT;
         return var10000 + input.trim().replaceAll("%", "\\\\%") + PERCENT;
      }
   }

   public static String fillSearchLike(String input, String oldChar, String newChar) {
      if (StringUtils.hasLength(input)) {
         String var10000 = PERCENT;
         return var10000 + input.trim().replace(oldChar, newChar) + PERCENT;
      } else {
         return null;
      }
   }

   public static String encodeKeyword(String input) {
      return StringUtils.hasLength(input) ? input.trim().replaceAll("%", "\\\\%").replaceAll("_", "\\\\_") : null;
   }
}
