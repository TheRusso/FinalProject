<div id="footer" class="container block" style="margin-bottom: 30px">
    <div class="container">
        <img src="<c:url value="/pages/motanka/img/separate_line.png" /> " alt="" class="img-fluid d-block mx-auto">
        <h2 class="text-center brusnika"><fmt:message key="footer.followUs" /></h2>
    </div>
    <div class="d-flex justify-content-center">
        <div style="width: 200px;" class="d-flex justify-content-around">
            <a href="https://www.instagram.com/motankaband/" target="_blank"><img src="<c:url value="/pages/motanka/img/instagram_bw.png" />" height="25px" alt=""></a>
            <a href="https://www.facebook.com/motankaband/" target="_blank"><img src="<c:url value="/pages/motanka/img/facebook_bw.png" /> " height="25px" alt=""></a>
            <a href="https://www.youtube.com/channel/UCngZgkmplUObMlkc1dOPXCQ" target="_blank"><img src="<c:url value="/pages/motanka/img/youtube_bw.png" /> " height="25px" alt=""></a>
            <a href="https://open.spotify.com/artist/71U8pkzbOG5ynnXw3ZtnGf?si=fcOHgx_aQNSlSk8XyZHifA"><img src="<c:url value="/pages/motanka/img/spotify_bw.png" /> " height="25px" alt=""></a>
        </div>
    </div>
    <p class="text-center" style="margin: 20px 0 0 0;">
        2020 Directed by The Russo
    </p>

    <ul style="position: absolute; bottom: -260px; left: 100px">
        <li>
            <form action="view" method="post">
                <input type="hidden" name="command" value="change_lang">
                <input type="hidden" name="lang" value="ru">
                <input type="submit" value="ru" class="text_from_input">
            </form>
        </li>
        <li>
            <form action="view" method="post">
                <input type="hidden" name="command" value="change_lang">
                <input type="hidden" name="lang" value="en">
                <input type="submit" value="en" class="text_from_input">
            </form>
        </li>
    </ul>

</div>