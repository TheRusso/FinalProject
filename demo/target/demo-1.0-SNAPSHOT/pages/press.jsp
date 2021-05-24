<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Register" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>
<%@include file="/jspf/header.jsp"%>



<div class="container block mt-50">

    <div class="container">
        <img src="<c:url value="/pages/motanka/img/separate_line.png"/> " alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika"><fmt:message key="press.press_kit" /></h1>
    </div>

    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="<c:url value="/pages/motanka/img/acoustic-1851248_1920.jpg"/>" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="<c:url value="/pages/motanka/img/acoustic-1851248_1920.jpg"/>" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="<c:url value="/pages/motanka/img/acoustic-1851248_1920.jpg"/>" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="<c:url value="/pages/motanka/img/acoustic-1851248_1920.jpg"/>" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="<c:url value="/pages/motanka/img/acoustic-1851248_1920.jpg"/>" alt="Third slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>


<div class="container-fluid block mt-50">
    <div class="row">
        <div class="col-sm-12 col-lg-6">
            <div class="container">
                <img src="<c:url value="/pages/motanka/img/separate_line.png"/>" alt="" class="img-fluid d-block mx-auto">
                <h1 class="display-2 text-center brusnika"><fmt:message key="press.bio" /> </h1>
            </div>
            <div class="container">
                <h1 class="display-4" style="font-size: 26px"><fmt:message key="press.city" />: <small><fmt:message key="press.lutsk" /></small></h1>
                <h1 class="display-4" style="font-size: 26px"><fmt:message key="press.genre" />: <small><fmt:message key="press.genre_content" /></small></h1>
                <h1 class="display-4" style="font-size: 26px"><fmt:message key="press.years" />: <small><fmt:message key="press.years_content" /></small></h1>
                <h1 class="display-4" style="font-size: 26px"><fmt:message key="press.label" />: <small><fmt:message key="press.label.content" /></small></h1>

                <h1>
                    <fmt:message key="press.short_biography" />:
                </h1>
                <p style="color: #CCC">
                    Гурт MOTANKA створений в 2015 році в м. Луцьк та складається з чотирьох музикантів: Віктор Жалнін — вокал, цимбали, keyboard; Анатолій Жалнін — гітара; Сергій Ходорчук — бас-гітара, окарини; Дмитро Дяченко — ударні, горловий спів <br>
                    <br><br>
                    Гурт відомий перемогою на W: O:A Metal Battle 2018 (2nd Winner) в рамках найбільшого фестивалю важкої музики в світі Wacken Open Air. В грудні 2018 музиканти підписали контракт з одним із найбільших метал-лейблів світу Napalm Records, де 7 червня 2019 року відбувся реліз їхнього першого альбому, що був раніше записаний на плівку в Берліні. Переможці the Best Ukrainian Metal Act 2019, номінанти YUNA 2020.
                    <br><br>
                    Загалом гурт вже встиг побувати на інших чисельних фестивалях Німеччини, Польщі та України та був фіналістом найбільших бенд-контестів в Німеччині: Emergenza, SPH Band Contest, Dittrock.<br><br>
                </p>
                <div class="but_down">
                    <a href="" download>
                        <div class="but_down_press"><fmt:message key="press.download_full_biography" /></div>
                    </a>
                </div>
                <div class="but_down">
                    <a href="" download>
                        <div class="but_down_press"><fmt:message key="press.download_press_release" /></div>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-lg-6 mt-lg-50">
            <div class="container">
                <img src="<c:url value="/pages/motanka/img/separate_line.png"/>" alt="" class="img-fluid d-block mx-auto">
                <h1 class="display-2 text-center brusnika"><fmt:message key="press.music" /></h1>
            </div>
            <div class="d-flex justify-content-center">
                <iframe src="https://open.spotify.com/embed/artist/71U8pkzbOG5ynnXw3ZtnGf" width="600px" height="480" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid block fluid_p">
    <div class="container">
        <img src="<c:url value="/pages/motanka/img/separate_line.png"/>" alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika"><fmt:message key="press.video" /></h1>
    </div>
    <div class="row">
        <div class="col-sm-12 col-lg-6">
            <div class="frame_blc">
                <iframe width="894" height="472" src="https://www.youtube.com/embed/Dx7lRWJyMeY" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            </div>
        </div>
        <div class="col-sm-12 col-lg-6">
            <div class="frame_blc">
                <iframe width="894" height="503" src="https://www.youtube.com/embed/GPO-V9Lw0dw" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid fluid_p mt-50">
    <div class="row">

        <div class="col-lg-6">
            <div class="container">
                <h1 class="display-2 text-center brusnika"><fmt:message key="press.rider" /></h1>
                <img src="<c:url value="/pages/motanka/img/separate_line.png"/>" alt="" class="img-fluid d-block mx-auto">
            </div>

            <div class="row mt-50">
                <div class="col-sm-3">
                    <div class="but_down">
                        <a href="" download>
                            <div class="but_down_press"><fmt:message key="press.download" /></div>
                        </a>
                    </div>
                </div>
                <div class="col-sm-6">
                    <h4 class="rider_text"><fmt:message key="press.set_list" /></h4>
                </div>
            </div>
            <div class="row mt-50">
                <div class="col-sm-3">
                    <div class="but_down">
                        <a href="" download>
                            <div class="but_down_press"><fmt:message key="press.download" /></div>
                        </a>
                    </div>
                </div>
                <div class="col-sm-6">
                    <h4 class="rider_text"><fmt:message key="press.set_list" /></h4>
                </div>
            </div>

        </div>

        <div class="col-lg-6">
            <div class="container">
                <h1 class="display-2 text-center brusnika"><fmt:message key="press.rep" /></h1>
                <img src="<c:url value="/pages/motanka/img/separate_line.png"/>" alt="" class="img-fluid d-block mx-auto">
            </div>
            <div class="mt-50">
                <h1 class="display-4" style="font-size: 26px"><fmt:message key="press.label" />: <small>Napalm Records</small></h1>
                <h1 class="display-4" style="font-size: 26px"><fmt:message key="press.manager" />: <small>Вероніка | @veronika.grass</small></h1>
            </div>
        </div>
    </div>
</div>



<%@ include file="/jspf/footer.jsp"%>

</body>

</html>