package com.ftmatters.crawler;

import lombok.Builder;
import lombok.Setter;

@Setter @Builder
public class TMDBRequestBodyBuilder {
    @Builder.Default public String airDateGTE = "";
    @Builder.Default public String airDateLTE = "2022-12-03";
    @Builder.Default public String certification = "";
    @Builder.Default public String certificationCounty = "CH";
    @Builder.Default public String firstAirDateGTE = "";
    @Builder.Default public String firstAirDateLTE = "";
    @Builder.Default public String ottRegion = "";
    @Builder.Default public int page = 0;
    @Builder.Default public String primaryReleaseDateGTE = "";
    @Builder.Default public String primaryReleaseDateLTE = "";
    @Builder.Default public String region = "";
    @Builder.Default public String releaseDateGTE = "";
    @Builder.Default public String releaseDateLTE= "2022-12-03";
    @Builder.Default public String showMe= "0";
    @Builder.Default public String sortBy= "popularity.desc";
    @Builder.Default public String voteAverageGTE= "0";
    @Builder.Default public String voteAverageLTE= "10";
    @Builder.Default public String voteCountGTE= "0";
    @Builder.Default public String withGenres= "";
    @Builder.Default public String withKeywords= "";
    @Builder.Default public String withNetworks= "";
    @Builder.Default public String withOriginCountry= "";
    @Builder.Default public String withOriginalLanguage= "";
    @Builder.Default public String withOttMonetizationTypes= "";
    @Builder.Default public String withOttProviders= "";
    @Builder.Default public String withReleaseType= "";
    @Builder.Default public String withRuntimeGTE = "0";
    @Builder.Default public String withRuntimeLTE = "400";

    public String getRequestBody() {
        return  String.format("air_date.gte=%s" +
                "&air_date.lte=%s" +
                "&certification=%s" +
                "&certification_country=%s" +
                "&debug=" +
                "&first_air_date.gte=%s" +
                "&first_air_date.lte=%s" +
                "&ott_region=%s" +
                "&page=%d" +
                "&primary_release_date.gte=%s" +
                "&primary_release_date.lte=%s" +
                "&region=%s" +
                "&release_date.gte=%s" +
                "&release_date.lte=%s" +
                "&show_me=%s" +
                "&sort_by=%s" +
                "&vote_average.gte=%s" +
                "&vote_average.lte=%s" +
                "&vote_count.gte=%s" +
                "&with_genres=%s" +
                "&with_keywords=%s" +
                "&with_networks=%s" +
                "&with_origin_country=%s" +
                "&with_original_language=%s" +
                "&with_ott_monetization_types=%s" +
                "&with_ott_providers=%s" +
                "&with_release_type=%s" +
                "&with_runtime.gte=%s" +
                "&with_runtime.lte=%s",
                airDateGTE,
                airDateLTE,
                certification,
                certificationCounty,
                firstAirDateGTE,
                firstAirDateLTE,
                ottRegion,
                page,
                primaryReleaseDateGTE,
                primaryReleaseDateLTE,
                region,
                releaseDateGTE,
                releaseDateLTE,
                showMe,
                sortBy,
                voteAverageGTE,
                voteAverageLTE,
                voteCountGTE,
                withGenres,
                withKeywords,
                withNetworks,
                withOriginCountry,
                withOriginalLanguage,
                withOttMonetizationTypes,
                withOttProviders,
                withReleaseType,
                withRuntimeGTE,
                withRuntimeLTE
            );
    }
}
