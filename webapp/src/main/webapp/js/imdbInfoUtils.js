function handleImdbInfo(imdbId, imdbInfoHandler) {
    $.get("http://www.omdbapi.com", { i: imdbId })
        .done(imdbInfoHandler);
}
