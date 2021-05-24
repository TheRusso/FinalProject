<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka" scope="page"/>
<%@ include file="jspf/head_page.jsp"%>

<body>

<style>
    body{
        background-image: url('/pages/motanka/img/back_back.png');
    }

    @font-face{
        font-family: Brusnika;
        src: url(<c:url value="/pages/motanka/font/Brusnika.otf"/>);
    }

    #logo_img{
        background-image: url(<c:url value="/pages/motanka/img/logo.png"/>);
    }
    @media (max-width: 991px) {
        #logo_img {
            background-image: url(<c:url value="/pages/motanka/img/logo_sm.png"/> );
            background-repeat: round;
            width: 420px;
        }
    }
</style>

<%@include file="jspf/header.jsp"%>

<div class="container-fluid d-sm-none  d-lg-block">
    <div id="social_topbar">
        <a href="https://www.instagram.com/motankaband/" target="_blank"><img src="/pages/motanka/img/instagram.png" height="25px" alt=""></a>
        <a href="https://www.facebook.com/motankaband/" target="_blank"><img src="/pages/motanka/img/facebook.png" height="25px" alt=""></a>
        <a href="https://www.youtube.com/channel/UCngZgkmplUObMlkc1dOPXCQ" target="_blank"><img src="/pages/motanka/img/youtube.png" height="25px" alt=""></a>
        <a href="https://open.spotify.com/artist/71U8pkzbOG5ynnXw3ZtnGf?si=fcOHgx_aQNSlSk8XyZHifA"><img src="/pages/motanka/img/spotify.png" height="25px" alt=""></a>
    </div>
    <img class="img-fluid" src="/pages/motanka/img/Head_back.png" alt="" style="margin-top: -130px">
</div>

<div class="container block">
    <div class="container">
        <img src="/pages/motanka/img/separate_line.png" alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika">Концерти</h1>
    </div>

    <img src="/pages/motanka/img/poster_v2_TxOer43.jpg" alt="" class="img-fluid d-block mx-auto">


</div>

<div class="container block mt-50" style="margin-bottom: 60px">
    <div class="container">
        <img src="/pages/motanka/img/separate_line.png" alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika">Новини</h1>
    </div>
    <div class="row">


        <div class="col-md-6 col-lg-4 over-hiden gray_hover" style="margin-bottom: 20px">
            <div class="card card-upg card-custom">
                <div class="bg-img"></div>
                <img src="/pages/motanka/img/photo_2019-06-07_12-28-13.jpg" alt="" class="card-img-top">
                <div class="card-img-overlay">
                    <h4 class="card-title text-white">Great website</h4>
                    <p class="card-text text-white">Pretext</p>
                    <a href="article.html" class="btn btn-primary bg-info btn-card2">Детальніше</a>
                </div>
            </div>
        </div>



    </div>
</div>

<div class="container block mt-50">
    <div class="container">
        <img src="/pages/motanka/img/separate_line_long.png" alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika">Альбоми</h1>
    </div>
    <div class="row d-flex justify-content-around">
        <div class="col-sm-12 col-md-6 col-lg-3 d-flex justify-content-center album_iframe">
            <iframe src="https://open.spotify.com/embed/artist/71U8pkzbOG5ynnXw3ZtnGf" width="300px" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        </div>
        <div class="col-sm-12 col-md-6 col-lg-3 d-flex justify-content-center album_iframe">
            <iframe src="https://open.spotify.com/embed/artist/71U8pkzbOG5ynnXw3ZtnGf" width="300px" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        </div>
        <div class="col-sm-12 col-md-6 col-lg-3 d-flex justify-content-center album_iframe">
            <iframe src="https://open.spotify.com/embed/artist/71U8pkzbOG5ynnXw3ZtnGf" width="300px" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        </div>
    </div>
</div>

<%@ include file="jspf/footer.jsp"%>

</body>

</html>