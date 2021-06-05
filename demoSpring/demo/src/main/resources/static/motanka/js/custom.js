
const add_buttons = document.querySelectorAll('[data-id]');
    add_buttons.forEach(btn => {
        btn.addEventListener("click", (evt => {
            addToCart(btn.dataset.id);
        }));
})

const update_buttons = document.querySelectorAll('[data-update-quant-id]');
    update_buttons.forEach(btn => {
        btn.addEventListener("change", (evt => {
            console.log(btn.dataset.updateQuantId + ' | ' +  evt.target.value);
            updateTextInput1(evt.target.value, btn.dataset.updateQuantId);
        }));
})

const delete_button = document.querySelectorAll('[data-delete-item-id]');
    delete_button.forEach(btn => {
        btn.addEventListener("click", (evt => {
            console.log(btn.dataset.deleteItemId);
            deleteFromCart(btn.dataset.deleteItemId);
        }));
})

function deleteFromCart(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: '/cart/delete',
        data_type: 'text',
        data: 'id=' + id
    });

    location.reload();
}

function updateTextInput1(val, id) {
    $.ajax({
        async: false,
        type: "POST",
        url: '/cart/update_quantity',
        data_type: 'text',
        data: 'id=' + id + '&quantity=' + val
    });
}

function addToCart(id){
    $.ajax({
        async: false,
        type: "POST",
        url: '/cart/add_to_cart',
        data_type: 'text',
        data: 'id=' + id,
        error: function (){
            alert('Error');
        },
        success: function (){
            alert('Success');
        }
    });
}


$(document).ready(function (e) {

    $('.color_box').click(function(){
        $('.color_box').removeClass('border_black')
        $(this).toggleClass('border_black')
        var color= $(this).attr('color');
        $('#color').val(color);
        $('#buy-button').data('color', color);
    });

    var size = 0;

    $('.box').click(function(){
        $('.box').removeClass('box_black');
        $('.box').addClass('box_white');
        $(this).toggleClass('box_black');
        $(this).toggleClass('box_white');

        size = $(this).text();
        if(!$(this).hasClass('over')){
            $('#buy-button').data('size', size);
        }else{
            $('#buy-button').data('size', 'over');
        }
    });

    var total_price = 0;

    $('#buy-button').on('click', function(e){
      e.preventDefault();
      var product_name = $('#buy-button').data('product-name');
      var product_id = $('#buy-button').data('id');
      var product_color = $('#buy-button').data('color');
      var product_size = $('#buy-button').data('size');
      var product_price = $('#price span').text();
      var product_img = $('.main_img').attr('src');



      var form = $('#item');
      var data = {};
      var csrf_token = form.find('input[name="csrfmiddlewaretoken"]').val();

      data.product_id = product_id;
      data.nmb = 1;
      data.title = product_name;
      data.img = product_img;
      data.price = product_price;
      data.size = product_size;

      data["csrfmiddlewaretoken"] = csrf_token;
      var url = form.attr('action');


      if(product_size != 'over'){
          $.ajax({
              url: url,
              type: 'POST',
              data: data,
              cache: true,
              success: function (data) {
                  console.log('OK');
              },
              error: function () {
                  console.log('ERROR')
              }
          });
          $('#item_count').text(+$('#item_count').text() + 1)
          $('#items').append('<div class="container-fluid product_item"><div class="row"><div class="col-md-5 pd-0"><img src="' + product_img + '" alt="" class="img-fluid"></div><div class="order_info col-md-7 pd-0"><p class="order_name text-left">' + product_name + '</p><span class="order_other text-left">' + product_size + '</span><p class="order_price order_other text-left"><span class="price" data-total="2330">' + product_price + '</span><span>grn</span></p><div class="container-fluid count_cont"><div class="minus_one"></div><input class="order_count" type="number" pattern="[0-9]*" min="1" max="7" value="1"><div class="plus_one"></div></div><span class="delete_item montserrat">Видалити</span></div></div> </div>');
          $('#order_items').removeClass('hidden');
      }



      console.log(product_name);
      console.log(product_id);
      console.log(product_color);
      console.log(product_size);
      console.log(product_img);

    })

    $('#order_list').on('click', function(e){
      $('#order_items').toggleClass('hidden');
    })



    $(document).on('mouseenter', '.product_item', function(e) {

      var context_input = $(this).find('.count_cont');
      var context = $(this);
      var input = context_input.find('.order_count');
      var price = context.find('.price').text();
      var total = price;


      $(this).find('.minus_one').unbind('click');
      $(this).find('.plus_one').unbind('click');

      //product count
      $(this).find('.minus_one').bind('click', function(e){
           var count = input.val();
    //                           console.log(count);
           if(count == input.attr('min')){
                //do nothing
            }else{
                input.val(count - 1);
                var value = +count - 1;

                //ajax

                var data = {};
                data.id = context.data('item-id');
                data.nmb = value;
                var csrf_token = $('input[name="csrfmiddlewaretoken"]').val();
                data["csrfmiddlewaretoken"] = csrf_token;
                console.log(csrf_token);
                console.log(data.nmb);
                console.log(data.id);
            }
      })

      $(this).find('.plus_one').bind('click', function(e){

            var count = input.val();
    //                            console.log(count);
            if(count == input.attr('max')){
                //do nothing
            }else{
                input.val(+count + 1);
                var value = +count + 1;
                total = price * value;

                //ajax

                var data = {}
                data.nmb = value
                data.id = context.data('item-id')
                var csrf_token = $('input[name="csrfmiddlewaretoken"]').val();
                data["csrfmiddlewaretoken"] = csrf_token;
                console.log(csrf_token);
                console.log(data.nmb);

                // var url = form.attr('action');
                //
                // $.ajax({
                // url: url,
                // type: 'POST',
                // data: data,
                // cache: true,
                // success: function (data) {
                //   console.log('OK');
                // },
                // error: function () {
                //   console.log('ERROR')
                // }
                //   })

            }
       });

      context.find('.delete_item').unbind('click');

      // delete item
      context.find('.delete_item').bind('click', function(e){
          e.preventDefault();

         var form = context.find('form')
         var data = {};
         var csrf_token = form.find('input[name="csrfmiddlewaretoken"]').val();
         var id = context.data('item-id');
         data.id = id;

          data["csrfmiddlewaretoken"] = csrf_token;
          var url = form.attr('action');
          $.ajax({
              url: url,
              type: 'POST',
              data: data,
              cache: true,
              success: function (data) {
                  console.log('OK');
                  context.remove();
                  $('#item_count').text($('#item_count').text() - 1);
              },
              error: function () {
                  console.log('ERROR');
              }
          })

      })

    })
})