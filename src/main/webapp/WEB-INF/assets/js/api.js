var HttpClient = function() {
		
	this.get = function(aUrl, aCallback) {
	var anHttpRequest = new XMLHttpRequest();
		anHttpRequest.onreadystatechange = function() {
				
			if (anHttpRequest.readyState == 4
						&& anHttpRequest.status == 200)
					aCallback(anHttpRequest.responseText);
			}
		
			anHttpRequest.open("GET", aUrl, true);
			anHttpRequest.send(null);
		}
	}

	var theurl = "http://localhost:8080/weaponry-csgo/weaponry";
	var client = new HttpClient();
	
	client.get(theurl, function(response) {
		var response1 = JSON.parse(response);
		myFunction(response1);
	});

	function myFunction(arr) {
		var images = "";
		var i;
		for (i = 0; i < arr.length; i++) {
			var x = i+1;
			images += '<div class="mySlides fade"> '
				    + '<div class="numbertext">'+ x +' / '+ arr.length +'</div>'
				    + '<img class="imgslide" src=" https://s3-sa-east-1.amazonaws.com/weaponry-csgo/' + arr[i].weapon +'" alt="'+ arr[i].name +'"/>'
					+ '<div class="text">' + arr[i].name + '</div>'
					+ '</div>'
		}
		document.getElementById("slide").innerHTML = images;
	}