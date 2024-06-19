FROM openjdk:17
ENV TZ=Asia/Seoul
COPY build/libs/stockChatServer-0.0.1.jar StockChartServer.jar
ENTRYPOINT ["java", "-jar", "StockChartServer.jar"]