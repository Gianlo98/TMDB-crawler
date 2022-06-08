# TMDB-crawler



This is a crawler for https://www.themoviedb.org/, which collects images of movies together with their names.

It retrieves the movies from IMDB and stores them in a queue. The queue automatically refills once it is empty. Moreover, it is also possible to check the presence of a movie in the database (``CHECK_FOR_DUPLICATES``) before inserting it into the queue, avoiding pooling the website with unuseful requests.

The queue is filled with the URL of popular movies. However, a new class that follows another movie's retrieving criteria can be easily created.

At a fixed interval set by the environmental variable ``CRAWL_RATIO``, the URL of the movie is polled from the queue, and then the movie's details are parsed from its page.

For each movie, only the name and the URL of its image are collected. Therefore, this information is stored in a SQLite database, indexed with the same id given by IMDB.

A database file ``crawler.db`` is created if not present inside the db folder of the working directory. Please ensure that this folder is present in the running environment.

This project is also shipped with two docker-compose files, one for "production" and one for development. The second one mounts a volume that allows you to interact with the ``crawler.db`` file. 

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
- ``CHECK_FOR_DUPLICATES``: to avoid parsing movies already present into the database. Performance might be slow (but you will be a better web citizen). [default ``True``]

To try them, you need to build the `tmdb-crawler_crawler` image with the `docker compose build` command and then run 

``docker run -e CRAWL_RATIO=10 -e CHECK_FOR_DUPLICATES=False tmdb-crawler_crawler``