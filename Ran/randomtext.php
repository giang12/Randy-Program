<?
$memory_limit = intval(ini_get('memory_limit')) * 1048576;
//randomize();
while (1 == 1) {
    echo PHP_EOL . "Enter a fuction to be call: ";
    $handle = fopen("php://stdin", "r");
    $line   = fgets($handle);
    callFunction(trim($line));
}
function callFunction($function)
{
    switch ($function) {
        case "help":
            help();
            break;
        case "line_count":
            line_count();
            break;
        case "randomize":
            randomize();
            break;
        case "quit":
            quit();
            break;
        case "sort_list":
            sort_list();
            break;
        default:
            echo "The function \"" . $function . "\" does not exist, type help for list of available functions" . PHP_EOL;
            break;
    }
    
}
function help()
{
    echo (PHP_EOL . "*************************************************" . PHP_EOL);
    echo ("* line_count -to count line in randommized_list.txt" . PHP_EOL);
    echo ("* randomize -to randomize the user list" . PHP_EOL);
    echo ("* sort_list -to sort the user list" . PHP_EOL);
    echo ("* help -help desk" . PHP_EOL);
    echo ("* quit -quit program" . PHP_EOL);
    echo ("*************************************************" . PHP_EOL);
    
}
function quit()
{
    exit(1);
}
function line_count()
{
    listFile();
    echo ("Which file? ");
    $handle = fopen("php://stdin", "r");
    $file   = trim(fgets($handle));
    $count  = 0;
    $file   = fopen($file, "r");
    while (!feof($file)) {
        $line = fgets($file);
        $count++;
    }
    fclose($file);
    echo $count . PHP_EOL;
}
function randomize()
{
    listFile();
    echo ("Which file? ");
    $handle = fopen("php://stdin", "r");
    $file   = trim(fgets($handle));
    $file   = fopen($file, "r");
    $array  = array();
    while (!feof($file)) {
        $line = fgets($file);
        echo $line . PHP_EOL;
        array_push($array, $line);
        if (memory_get_usage() > $GLOBALS['memory_limit'] / 2) {
            shuffle($array);
            foreach ($array as $value) {
                write("randommized_list.txt", $value);
            }
            $array = array();
        }
    }
    shuffle($array);
    foreach ($array as $value) {
        write("randommized_list.txt", $value);
    }
    unset($array);
    fclose($file);
}
function sort_list()
{
    listFile();
    echo ("Which file? ");
    $handle   = fopen("php://stdin", "r");
    $file     = trim(fgets($handle));
    $tempFile = array();
    
    mkdir("temp");
    $file  = fopen($file, "r");
    $count = 0;
    $start = microtime(TRUE);
    //first read in all, split to small sorted lists
    while (!feof($file)) {
        $line = fgets($file);
        array_push($tempFile, $line);
        echo getIndex($line) . " | " . $line . PHP_EOL;
        //sort and save out if reach memory_limit
        if (memory_get_usage() > $GLOBALS['memory_limit'] / 10) {
            $tempFile = merge_sort($tempFile);
            
            foreach ($tempFile as $value) {
                write("temp/" . $count, $value);
            }
            /*$tempFile=implode("",$tempFile);
            write("temp/".$count,$tempFile);*/
            $tempFile = array();
            $count++;
        }
    }
    $tempFile = merge_sort($tempFile);
    foreach ($tempFile as $value) {
        write("temp/" . $count, $value);
    }
    /*$tempFile=implode("",$tempFile);
    write("temp/".$count,$tempFile);*/
    unset($tempFile);
    unset($count);
    fclose($file);
    $mergeFile = array();
    foreach (glob("temp/*") as $filename) {
        array_push($mergeFile, fopen($filename, "r"));
    }
    finalMerge($mergeFile);
    foreach (array_merge(glob("temp/*"), glob("temp/.*")) as $filename) {
        unlink($filename);
    }
    rmdir("temp");
    echo (PHP_EOL . memory_get_usage() . ' | memory on heap after sort' . PHP_EOL);
    echo ((microtime(TRUE) - $start) . ' milliseconds taken' . PHP_EOL);
    
}
function finalMerge($file = array())
{
    $value = array();
    $stop  = FALSE;
    while (!$stop) {
        
        $lowestKey = NULL;
        $lowest    = NULL;
        
        foreach ($file as $key => $in) {
            if (isset($value[$key])) {
                //do nothing
            } else if (!isset($value[$key]) && !feof($in)) {
                $value[$key] = fgets($in);
            } else if (!isset($value[$key]) && feof($in)) {
                $value[$key] = NULL;
            }
        }
        foreach ($value as $index => $line) {
            if ((is_null($lowest) || intval($line) <= intval($lowest)) && !is_null($line)) {
                $lowestKey = $index;
                $lowest    = $line;
            }
        }
        if (!is_null($lowest)) {
            write("new_sorted_file.txt", $lowest);
            unset($value[$lowestKey]);
        } else {
            $stop = TRUE;
        }
    }
    foreach ($file as $terminate) {
        fclose($terminate);
    }
}
function getIndex($string)
{
    return intval(substr($string, 0, strpos($string, "-")));
}
function merge_sort($unsorted_array)
{
    
    //if array is but one element, array is sorted, so return as is
    if (sizeof($unsorted_array) == 1)
        return $unsorted_array;
    
    //bifurcate unsorted array
    $array2 = array_splice($unsorted_array, (sizeof($unsorted_array) / 2));
    
    //recursively merge-sort and return
    return merge(merge_sort($unsorted_array), merge_sort($array2));
    
}
function merge($left, $right)
{
    
    //init an empty output array
    $output = array();
    
    //loop through the arrays while at least one still has elements left in it
    while (!empty($left) || !empty($right))
    //one of the arrays is empty, so the last man standing wins...
        if (empty($left) || empty($right))
            $output[] = (empty($right)) ? array_shift($left) : array_shift($right);
        
        //both arrays still have elements, looks like we have a showdown...custom intval- bad but quick fix ^^
        else
            $output[] = (intval($left[0]) <= intval($right[0])) ? array_shift($left) : array_shift($right);
    
    //pass back the output array	
    return $output;
}
function listFile()
{
    echo (PHP_EOL . " LIST OF FILE: ");
    foreach (glob("*.*") as $filename) {
        echo ($filename) . " | ";
    }
    echo (PHP_EOL);
}
function write($path, $string)
{
    $fp = fopen($path, 'a');
    fwrite($fp, $string);
    fclose($fp);
}
?>
