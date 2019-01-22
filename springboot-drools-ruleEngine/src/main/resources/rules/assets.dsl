[condition][gets all assets]Assets with = $asset: Asset()
[condition][]- currency "{currency}" = currency=="{currency}"
[condition][]- rating "{r1}", "{r2}" = rating in ("{r1}", "{r2}")
[condition][]- institution "{i}"= institution=="{i}"
[condition][]- portfolio "{p}"= portfolio=="{p}"
[condition][]- snapshotdate "{date}" = snapshotDate=="{date}"

[consequence]Display = System.out.println($asset);insert(new AssetsToCalcutale($asset.getAmount(),$asset.getPercentage(),$asset.getNominal()));
[consequence] Display2 = System.out.println("Hello");

[condition]assets are eligible= $issuerLimit : Double() from accumulate( AssetsToCalcutale($x:amount,$y:percentage), sum($x*$y))
$totalNominal: Double() from accumulate( AssetsToCalcutale($s:nominal), sum($s))
[consequence]Calculate issuer limit=System.out.println("Issuer limit: "+$issuerLimit);
[consequence]Is max limit exceeded=
String max = 3000000d<$issuerLimit ? "TRUE":"FALSE";
System.out.println("MAX limit exceeded: "+ max);
[consequence]Is total nominal exceeded=
String x=$totalNominal >$issuerLimit?"TRUE":"FALSE";
System.out.println("Total nominal exceeded=: "+x);