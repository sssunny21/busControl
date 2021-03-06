<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.start { display: none; }
</style>
<script>
//한 항목만 선택 가능
function check_only(chk){
    var obj = document.getElementsByName("allocateid");
    for(var i=0; i<obj.length; i++){
        if(obj[i] != chk){
            obj[i].checked = false;
        }
    }
}
//선택 안했을 경우
$(function(){
	$('#drive').click(function() {
	    var ischecked = $("input:checkbox[name=allocateid]").is(":checked");
	    if(!ischecked){
	        alert("차량을 선택해주세요.");
	        location.href = "operateList.gnt";
	        event.preventDefault();
	    }
	});
});
</script>
<link rel="stylesheet" href="https://openlayers.org/en/v4.0.0/css/ol.css" type="text/css">
  <!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
<script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
<script src="https://openlayers.org/en/v4.0.0/build/ol.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href = "/bus/res/operate.css" />

<h4>모니터링</h4>
<jsp:useBean id="now" class="java.util.Date" />
<form:form method="post" modelAttribute="allocate">
	<div class="panel-body">
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
		<h6 align="right">현재날짜 : ${today }</h6>
		<h5>전체노선 : 문원체육공원 -> 과천역 -> 과천지구대 -> 과천시청 -> 정부과천청사역 -> 약수교회 ->
			문원체육공원</h5>
		<table class="table table-bordered" id="table_s">
			<thead>
				<tr>
					<th>선택</th>
					<th>차량번호</th>
					<th>차랑상태</th>
					<th>배정된 기사</th>
					<th>운행 여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="allocateList" items="${allocateList}">
				<c:if test="${allocateList.state eq '운행' }">
					<tr>
						<td><input type="checkbox" name = "allocateid" value = "${allocateList.operateid }" onclick="check_only(this)"></td>
						<td>${allocateList.bus_num }</td>
						<td>${allocateList.state}</td>
						<td>${allocateList.name }</td>
						<td class = "start" id="${allocateList.operateid }">시작</td>
						<td class = "end">대기</td>
					</tr>
				</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<center>
	<button id="drive" type="button" class="btn btn-info">운행 시작</button>
	</center>
</form:form>

<div id="map" class="map" style="margin-top: 15px;">
 <div id="popup" class="ol-popup">
   <a href="#" id="popup-closer" class="ol-popup-closer"></a>
   <div id="popup-content"></div>
</div>
<div id = "busOverlay" style="width: 108px;">
	<center>
	<div id = "bus-content"></div>
	</center>
</div>
</div>

<script>
	var content = document.getElementById('popup-content');
	var busContent = document.getElementById('bus-content');
	var closer = document.getElementById('popup-closer');
	var selectNum = 0;
	/***지도 변수***/
	var start = new Array();
	var busStopList = new Array();
	var xy = new Array();
	var bus_num = new Array();
	var routeXY = new Array();
	var limit_passenger = new Array();
	var operateid = 0;

	start.push("${start.node_name}");
	xy.push("${start.x}", "${start.y}");
	<c:forEach var="busStopList" items="${busStopList}">
		busStopList.push("${busStopList.node_name}");
		xy.push("${busStopList.x}", "${busStopList.y}");
	</c:forEach>
	var xyLength = xy.length;
	<c:forEach var="allocateList" items="${allocateList}">
		bus_num.push("${allocateList.bus_num}");
		limit_passenger.push("${allocateList.limit_passenger}");
	</c:forEach>
	<c:forEach var="sequenceList" items="${sequenceList}">
		routeXY.push("${sequenceList.x}","${sequenceList.y}");
	</c:forEach>
	
	/***Feature들**/
	var bus = new ol.Feature({
		geometry : new ol.geom.Point([ xy[0], xy[1] ]),
		cur_passenger : 0,
		oper_count : 0,
		accu_passenger : 0,
		num : 0
	});
	var start = new ol.Feature({
		geometry : new ol.geom.Point([ xy[0], xy[1] ]),
		name : '차고지 : ' + start[0],
		num : 0,
		passenger : 0,
		bus_num : bus_num[0]
	});
	var b1 = new ol.Feature({
		geometry : new ol.geom.Point([ xy[2], xy[3] ]),
		name : '1번 정류장 : ' + busStopList[0],
		num : 1,
		passenger : Math.floor(Math.random() * 10) + 1,
		bus_num : bus_num[0]
	});
	var b2 = new ol.Feature({
		geometry : new ol.geom.Point([ xy[4], xy[5] ]),
		name : '2번 정류장 : ' + busStopList[1],
		num : 2,
		passenger : Math.floor(Math.random() * 10) + 1,
		bus_num : bus_num[0]
	});
	var b3 = new ol.Feature({
		geometry : new ol.geom.Point([ xy[6], xy[7] ]),
		name : '3번 정류장 : ' + busStopList[2],
		num : 3,
		passenger : Math.floor(Math.random() * 10) + 1,
		bus_num : bus_num[0]
	});
	var b4 = new ol.Feature({
		geometry : new ol.geom.Point([ xy[8], xy[9] ]),
		name : '4번 정류장 : ' + busStopList[3],
		num : 4,
		passenger : Math.floor(Math.random() * 10) + 1,
		bus_num : bus_num[0]
	});
	var b5 = new ol.Feature({
		geometry : new ol.geom.Point([ xy[10], xy[11] ]),
		name : '5번 정류장 : ' + busStopList[4],
		num : 5,
		passenger : Math.floor(Math.random() * 10) + 1,
		bus_num : bus_num[0]
	});

	/***Feature 스타일***/
	var iconStyleStart = new ol.style.Style({
		image : new ol.style.Icon(({
			anchor : [ 0.5, 60 ],
			anchorXUnits : 'fraction',
			anchorYUnits : 'pixels',
			src : 'http://imageshack.com/a/img924/4316/tnYvHg.png',
			scale : 0.6
		}))
	});

	var iconStyleBusStop = new ol.style.Style({
		image : new ol.style.Icon(({
			anchor : [ 0.5, 60 ],
			anchorXUnits : 'fraction',
			anchorYUnits : 'pixels',
			src : 'http://imageshack.com/a/img924/4075/xEC49r.png',
			scale : 0.6
		}))
	});
	var iconStyleBus = new ol.style.Style({
		image : new ol.style.Icon({
			anchor : [ 0.5, 60 ],
			anchorXUnits : 'fraction',
			anchorYUnits : 'pixels',
			src : 'http://imageshack.com/a/img922/7168/vJVVAH.png',
			scale : 1
		})
	});
	
	bus.setStyle(iconStyleBus);
	start.setStyle(iconStyleStart);
	b1.setStyle(iconStyleBusStop);
	b2.setStyle(iconStyleBusStop);
	b3.setStyle(iconStyleBusStop);
	b4.setStyle(iconStyleBusStop);
	b5.setStyle(iconStyleBusStop);
	
	/***지도 설정***/
	var vectorSource = new ol.source.Vector({
		features : [ start, b1, b2, b3, b4, b5, bus]
	});

	var vectorLayer = new ol.layer.Vector({
		source : vectorSource
	});

	raster = new ol.layer.Tile({
		source : new ol.source.OSM()
	});

	var pureCoverage = false;

	var format = 'image/png';
	var bounds = [ 126.96002166748066, 37.38964813232441, 127.04876739501934, 37.47839385986309 ];
	var mousePositionControl = new ol.control.MousePosition({
		className : 'custom-mouse-position',
		target : document.getElementById('location'),
		coordinateFormat : ol.coordinate.createStringXY(5),
		undefinedHTML : '&nbsp;'
	});
	var untiled = new ol.layer.Image({
		source : new ol.source.ImageWMS({
			ratio : 1,
			url : 'http://192.168.1.156:8080/geoserver/test/wms',
			params : {
				'FORMAT' : format,
				'VERSION' : '1.1.1',
				STYLES : '',
				LAYERS : 'test:eunsun',
			}
		})
	});
	var tiled = new ol.layer.Tile({
		visible : false,
		preload : 4,
		source : new ol.source.TileWMS({
			url : 'http://192.168.1.156:8080/geoserver/test/wms',
			params : {
				'FORMAT' : format,
				'VERSION' : '1.1.1',
				tiled : true,
				STYLES : '',
				LAYERS : 'test:eunsun',
				tilesOrigin : 126.96002166748066 + "," + 37.38964813232441
			}
		})
	});

	var projection = new ol.proj.Projection({
		code : 'EPSG:4326',
		units : 'degrees',
		axisOrientation : 'neu',
		global : true
	});
	var view = new ol.View({
		projection : projection,
		center : [126.99672, 37.43083]
	});
	var map = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}).extend([ mousePositionControl ]),
		target : document.getElementById('map'),
		layers : [ raster, untiled, tiled, vectorLayer ],
		loadTilesWhileAnimating : true,
		view : view
	});
	
	/***정류장 정보 팝업 오버레이***/
	var element = document.getElementById('popup');
	var popup = new ol.Overlay({
		element : element,
		positioning : 'bottom-center',
		stopEvent : false,
		offset : [ 0, -50 ]
	});
	map.addOverlay(popup);
	
	/***버스 탑승인원 오버레이***/
	var busOverlay = new ol.Overlay({
		element : document.getElementById('busOverlay'),
		offset : [10, -75]
	});
	map.addOverlay(busOverlay);
	
	closer.onclick = function() {
		popup.setPosition(undefined);
        closer.blur();
        return false;
      };

	map.getView().on('change:resolution', function(evt) {
		var resolution = evt.target.get('resolution');
		var units = map.getView().getProjection().getUnits();
		var dpi = 25.4 / 0.28;
		var mpu = ol.proj.METERS_PER_UNIT[units];
	});

	map.getView().setZoom(15.5); // zoom 설정
	
	/***정류장 팝업 정보들***/
	map.on('click', function(evt) {
		var feature = map.forEachFeatureAtPixel(evt.pixel, function(feature) {
			return feature;
		});
		if (feature) {
			var coordinates = feature.getGeometry().getCoordinates();
			if(bus.getGeometry().getCoordinates().toString() == start.getGeometry().getCoordinates().toString()){
				content.innerHTML = feature.get('name') +' <br>'+feature.get('bus_num') + ' : 차고지 출발 대기' ;
			}else{
				var busLocation = feature.get('num') - bus.get('num');
				if(busLocation > 0){
					content.innerHTML = feature.get('name') +' <br>'+feature.get('bus_num') + ' : '+busLocation+'정류장 전';
				}else if(busLocation == 0){
					content.innerHTML = feature.get('name') +' <br>'+feature.get('bus_num') + ' : 탑승 완료';
				}else{
					content.innerHTML = feature.get('name') +' <br>'+feature.get('bus_num') + ' : 지나간 차량';
				}
			}
			popup.setPosition(coordinates);
		} else {
			closer.blur();
	        return false;
		}
	});

	/***버스 이동 함수***/
	function flyTo(location, index, done) {
		var parts = 1;
		var called = false;
		function callback(complete) {
			--parts;
			if (called) {
				return;
			}
			if (parts === 0 || !complete) {
				called = true;
				done(complete);
			}
		}
		bus.setGeometry(location);
		bus.set('num',index);
		busOverlay.setPosition(bus.getGeometry().getCoordinates());
		callback(true);
	}

	/***운행 함수***/
	function drive(bus, operateid) {
		var locations = [start, b1, b2, b3, b4, b5, start];
		var index = -1;
		var location_index = 0;
		function next(more) {
			if (more) {
				++index;
				var now = new Date().getTime();
				if (index < routeXY.length) {
					var delay = index === 0 ? 0 : 1000; //1초
					setTimeout(function() {
						var current = new ol.geom.Point([ routeXY[index], routeXY[index+1] ]);
						for(var i = 0; i < locations.length; i++){
							if(current.getCoordinates().toString() == locations[i].getGeometry().getCoordinates().toString()){
								console.log("현재"+bus.get('cur_passenger')+" 탈사람: "+locations[i].get('passenger')+" i"+i);
								 if(bus.get('cur_passenger') + locations[i].get('passenger') >= limit_passenger[selectNum]){
									busContent.innerHTML = limit_passenger[selectNum] - bus.get('cur_passenger')+'명 탑승 <br>*탑승인원 제한*';
									bus.set('cur_passenger', bus.get('cur_passenger') + (limit_passenger[selectNum] - bus.get('cur_passenger')));
									location_index = i; 
									for(var i = 0; i < locations.length; i++){
										locations[i].set('passenger',0);
									}
								 }else{
									bus.set('cur_passenger', bus.get('cur_passenger')+ locations[i].get('passenger'));
									busContent.innerHTML = locations[i].get('passenger')+ '명 탑승';
									location_index = i;
								 }
							}
						}
						index++;
						flyTo(current,location_index, next);
					}, delay);
				} else {
					current = new ol.geom.Point([ routeXY[0], routeXY[1] ]);
					bus.setGeometry(current);
					bus.set('oper_count', 1);
					bus.set('accu_passenger', bus.get('cur_passenger'));
					bus.set('cur_passenger', 0);
					console.log("count : "+bus.get('oper_count')+"accu : "+bus.get('accu_passenger'));
					var afterOperate = {
						"operateid" : operateid,
						"oper_count" : bus.get('oper_count'),
						"accu_passenger" : bus.get('accu_passenger')
					};

					$.ajax({
						url : "/bus/operate/operateList.gnt",
						type : 'POST',
						data : afterOperate,
						success : function(data) {
							alert("운행 완료 \n총 "+ bus.get('accu_passenger')+ "명 탑승!");
							jQuery('.end').show();
							jQuery('.start').hide();
						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert("에러발생 \n" + textStatus + " : "+ errorThrown);
							self.close();
						}
					});//운행완료
				}
			} else {
				alert('운행 취소');
			}
		}
		next(true);
	}
	$('#drive').click(function(){
		var obj = $("input:checkbox[name=allocateid]");
		for(var i = 0; i < obj.length; i++){
		    if(obj[i].checked == true){
		    operateid = obj[i].value;
		    $(obj[i]).parents('td').siblings(".start").css('display','block');
		    $(obj[i]).parents('td').siblings(".end").css('display','none');
		    //parents로 td의 상위요소를 찾는다. 상위요소중에서 stard, end 라는 클래스 이름을 가진 하위요소를 찾아서 css값을 변경.
		    selectNum = i;
		    }
		  }
		drive(bus, operateid);
	})
</script>