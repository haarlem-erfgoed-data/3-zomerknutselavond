

# install.packages (install once only)
install.packages("httr")

# load libraries (install first, if not already done so)
library(jsonlite)
library(dplyr)
library(tidyr)
library(httr)
library(ggmap)



df <- fromJSON("http://www.verdwenengebouwen.nl/search/json/?q=")
df2 <- df # fail save (copy)

## data cleaning
df2 <- mutate(df2, 
              endmin_cl = ifelse(endmin == 0, NA, as.numeric(endmin)),
              endmax_cl = ifelse(endmax == 0, NA, as.numeric(endmax)),
              end_est = ifelse(!is.na(endmin_cl) & is.na(endmax_cl), 
                               endmin_cl, 
                               ifelse(is.na(endmin_cl) & !(is.na(endmax_cl)),
                                      endmax_cl, endmin_cl)))



summary(df2$endmin_cl)
summary(df2$endmax_cl)
summary(df2$end_est)

# mapping
nlmap <- get_map("Amersfoort, Netherlands", zoom = 6)
ggmap(nlmap)


lost_date <- ggmap(nlmap,
                   base_layer=ggplot(aes(x=as.numeric(lon),
                                                   y=as.numeric(lat)), 
                                               data=df2),
                   extent = "device", maprange=FALSE) + 
    geom_jitter(aes(colour = end_est)) + 
    scale_colour_gradient(low = "green", high = "red") +
    theme_bw(base_size = 16) + 
    labs(x = "longitude", y = "latitude", title = 
             "Position and year of buildings getting 'lost'" )
lost_date



