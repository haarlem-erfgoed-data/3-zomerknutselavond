
# load libraries (install first, if not already done so)
library(jsonlite)
library(dplyr)
library(tidyr)


df <- fromJSON("http://www.verdwenengebouwen.nl/search/json/?q=")
df2 <- df # fail save (copy)

## data cleaning
df2 <- mutate(df2, 
              endmin_cl = ifelse(endmin == 0, NA, as.numeric(endmin)),
              endmax_cl = ifelse(endmax == 0, NA, as.numeric(endmax))
              )

                
summary(df2$endmin_cl)
