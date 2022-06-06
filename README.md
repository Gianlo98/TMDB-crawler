# TMDB-crawler
A crawler for themoviedb.org which collects images of movies together with their names.

---
## Run commands

### Normal execution
- ````docker compose build````
- ````docker compose up````

### Dev environment
This environment maps the database file into the `db` folder
- ````docker compose build````
- ````docker compose up -f docker-compose.dev.yaml````

### Environment variables
- ``CRAWL_RATIO``: the number of requests per second. [default `1`]
- ``CHECK_FOR_DUPLICATES``: to avoid parsing movies already present into the database. Performance might be slow (but you will be a better web citizen). [default `True]

To try them, you need to build the `tmdb-crawler_crawler` image with the `docker compose build` command and then run 

``docker run -e CRAWL_RATIO=10 -e CHECK_FOR_DUPLICATES=False tmdb-crawler_crawler``