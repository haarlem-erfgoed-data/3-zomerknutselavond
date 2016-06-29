

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
df2$end_est


summary(df2$endmin_cl)
summary(df2$endmax_cl)
summary(df2$end_est)


df2 <- mutate(df2, 
              startmin_cl = ifelse(startmin == 0, NA, as.numeric(startmin)),
              startmax_cl = ifelse(startmax == 0, NA, as.numeric(startmax)),
              start_est = ifelse(!is.na(startmin_cl) & is.na(startmax_cl), 
                               startmin_cl, 
                               ifelse(is.na(startmin_cl) & !(is.na(startmax_cl)),
                                      startmax_cl, startmin_cl)))
df2$start_est



pp <- ggplot(df2, aes(df2$start_est, end_est)) +
    geom_jitter(aes(colour = type), shape = "o", alpha = .7) + 
    theme_bw(base_size = 16) + 
    scale_colour_discrete(guide = FALSE) +
    labs(x = "start year", y = "end year", title = 
             "Start year, end year and type" ) 
pp

ggsave(filename = "~/git/3-zomerknutselavond/img/startYr_endYr_type.png", pp)





# mapping
nlmap <- get_map("Amersfoort, Netherlands", zoom = 7, maptype = "satellite")
ggmap(nlmap, darken = .6)


summary(df2$end_est)


df2s <- subset(df2, end_est >= 1500)


lost_date <- ggmap(nlmap,
                   base_layer=ggplot(aes(x=as.numeric(lon),
                                                   y=as.numeric(lat)), 
                                               data=subset(df2s)),
                   extent = "device", maprange = FALSE) + 
    geom_jitter(aes(colour = end_est), alpha = .9, shape = "O") + 
    scale_colour_gradient(low = "green", high = "red",
                          guide_legend(title = "year lost")) +
    theme_bw(base_size = 16) + 
    labs(x = "longitude", y = "latitude", title = 
             "Position and year of buildings getting 'lost' (1500-2015)" ) 
    
lost_date

ggsave(filename = "~/git/3-zomerknutselavond/img/lost_buildings_1500_2015.png")


lost_type <- ggmap(nlmap,
                   base_layer=ggplot(aes(x=as.numeric(lon),
                                         y=as.numeric(lat)), 
                                     data=subset(df2s)),
                   extent = "device", maprange = FALSE) + 
    geom_jitter(aes(colour = as.factor(type)), alpha = .9) +
    theme_bw(base_size = 16) + 
    labs(x = "longitude", y = "latitude", title = 
             "Position and type of buildings getting 'lost' (1500-2015)" ) 

lost_type




# writing out to file
names(df)

df3 <- df2[, 1:16]
write.csv(df3, file = "~/git/3-zomerknutselavond/data/json2R_clean.csv")


tm_shape(NLD_muni) + tm_bubbles()





