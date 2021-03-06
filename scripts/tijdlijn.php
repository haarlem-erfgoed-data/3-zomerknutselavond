<?

if($_GET['nr']==1 || !isset($_GET['nr'])){
	$title = "Watertorens";
	$search = "http://www.verdwenengebouwen.nl/search/json/?q=&type=http://vocab.getty.edu/aat/300006204";
	$interval = 5;
}

if($_GET['nr']==2){
	$title = "Stadspoorten";
	$search = "http://www.verdwenengebouwen.nl/search/json/?q=&type=http://vocab.getty.edu/aat/300005080";
	$interval = 25;
}

if($_GET['nr']==3){
	$title = "Kerken in Haarlem";
	$search = "http://www.verdwenengebouwen.nl/search/json/?q=haarlem&type=http://vocab.getty.edu/aat/300007466";
	$interval = 15;
}

if($_GET['nr']==4){
	$title = "Amsterdam";
	$search = "http://www.verdwenengebouwen.nl/search/json/?q=amsterdam";
	$interval = 20;
}


$json = file_get_contents($search);
$data = json_decode($json,true);

$bs = array();
$startyear = 3000;
$endyear = 0;

foreach ($data as $key => $v) {
	if( ($v['startmin']>0||$v['startmax']>0) && ($v['endmin']>0||$v['endmax']>0)){
		$start = $v['startmin'];
		if($v['startmin']==0){
			$start = $v['startmax'];
		}
		$end = $v['endmax'];
		if($v['endmax']==0){
			$end = $v['endmin'];
		}
		$r = rand(0,255);
		$g = rand(0,255);
		$b = rand(0,255);
		$color = "rgb(" . $r . "," . $g . "," . $b . ")"; 

		$bs[] = array("start"=>$start, "end"=>$end, "data"=>$v, "color"=>$color);
		if($start < $startyear){
			$startyear = $start;
		}
		if($end > $endyear){
			$endyear = $end;
		}
	}
}

foreach ($bs as $key => $row) {
    $startjaar[$key]  = $row['startyear'];
    $endjaar[$key] = $row['endyear'];
}
array_multisort($startjaar, SORT_ASC, $bs);


$divs = ($endyear - $startyear) / $interval;
$width = $divs * 30 + 60;

?>
<html>

<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<title>tijdlijn verdwenen gebouwen</title>
<style type="text/css">

	body{
		
		font-family: arial;
	}
	
	#container{
		width: <?= $width ?>px;
		margin: auto;
		margin-top: 40px;
		min-height: 2000px;
	}
	.year{
		width: 25px;
		float: left;
		margin-right: 5px;
		text-align: center;
	}
	.label{
		transform:rotate(300deg);
		margin-top: 10px;
		margin-bottom: 10px;
		margin-left: 10px;
		font-weight: bold;
	}
	.building{
		background-color: #000;
		width: 12px;
		height: 12px;
		border-radius: 6px;
		margin: auto;
		margin-bottom: 3px;
	}
	.img{
		display: none;
		width: 200px;
		height: 200px;
		overflow: hidden;
		border-radius: 100px;
		background-color: #000;
		position: absolute;
		margin-left: 20px;
	}
	.img img{
		width: 200%;
		margin-left: -100px;
		margin-top: -20px;
	}
	h1{
		text-align: center;
	}

</style>

</head>

<body>



<div id="container">

	<h1><?= $title ?></h1>


	<? for($i=$startyear; $i<=$endyear; $i+=$interval){ ?>

		<div class="year">
			<div class="label">
				<?= $i ?>
			</div>
			<? foreach ($bs as $k => $v) { ?>
				<? if($v['start']<=$i && $v['end']>=$i){ ?>
					<div style="background-color:<?= $v['color'] ?>;" class="building" onclick="window.open('http://www.verdwenengebouwen.nl/gebouw/<?= $v['data']['id'] ?>');">
						<div class="img">
							<img src="<?= $v['data']['images'][0]['img_file'] ?>" /><br />
							<?= $v['data']['name'] ?>, <?= $v['data']['place_name'] ?> 
						</div>
					</div>
				<? } ?>
			<? } ?>
		</div>


	<? } ?>
</div>

<script type="text/javascript">
	
	$('.building').hover(
		function() {
    		$(this).find('.img').show();
  		}, 
  		function() {
    		$(this).find('.img').hide();
  		}
	);
	
</script>


</body>
</html>