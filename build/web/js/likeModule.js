/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function doLike(pid, email)
{
    console.log(pid + " " + email);

    // attatch data into object 'like_data'
    const like_data = {
        user_email: email,
        post_id: pid,
        operation: 'like'
    };

    // now pass into servlet in the form of ajax

    $.ajax({
        url: "LikeServlet",
        data: like_data,
        success: function (data, textStatus, jqXHR) {
            console.log(data);

            // if like successfully then 'data = true'
            // then target class='like_counter' and update likes
            if (data.trim() == 'true')
            {
                let c = $(".like_counter").html();
                c++;
                $(".like_counter").html(c);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });

}