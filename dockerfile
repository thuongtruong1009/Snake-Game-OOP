FROM openjdk:8
WORKDIR /src
COPY . .
RUN chmod a+x ./*
ENTRYPOINT ["sh","/src/container/build.sh"]