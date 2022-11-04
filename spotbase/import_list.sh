#!/bin/bash

echo $(wget $1 -O -) >> $(pwd)/content.txt
