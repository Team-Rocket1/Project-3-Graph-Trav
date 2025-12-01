# Simple helper to generate javadoc for the project
param(
    [string] $outDir = "docs",
    [switch] $IncludePrivate
)

if (-not (Get-Command javadoc -ErrorAction SilentlyContinue)) {
    Write-Error "javadoc tool not found. Please install a JDK and ensure javadoc is on your PATH."
    exit 1
}

$packages = "ADTPackage GraphPackage"
$sourcepath = "src"
$flags = "-d $outDir -sourcepath $sourcepath"
if ($IncludePrivate) { $flags += " -private" }
# Disable doclint to avoid warnings that might block older JDKs
$flags += " -Xdoclint:none"

javadoc $flags $packages

if ($LASTEXITCODE -ne 0) {
    Write-Error "javadoc failed with exit code $LASTEXITCODE"
} else {
    Write-Host "Javadoc generated under $outDir"
}
