FROM image

COPY build/distributions/*boot*.zip /coroutineapp.zip

RUN unzip -o coroutineapp && \
  rm -rf *.zip && \
  mv coroutineapp-* coroutineapp

EXPOSE 7770

ENTRYPOINT [ "/coroutineapp/bin/coroutineapp", "-Djava.security.egd=file:/dev/./urandom"]
