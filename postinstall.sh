#!/bin/sh

# rn-nodeify
# temporary hack due to https://github.com/facebook/react-native/issues/4968

node_libraries=(
  assert
  buffer
  console
  constants
  crypto
  dns
  domain
  events
  fs
  http
  https
  inherits
  net
  os
  path
  process
  punycode
  querystring
  stream
  string_decoder
  timers
  tty
  url
  util
  vm
  zlib
)

nodeify_list="${node_libraries[0]}$(printf ",%s" "${node_libraries[@]:1}")"

./node_modules/.bin/rn-nodeify \
  --yarn \
  --hack \
  --install "$nodeify_list"

# end hack

echo

# symlink for mapbox framework
ln -s ../node_modules/react-native-mapbox-gl/ios/Mapbox.framework ios

# symlink for re-natal
if ! [ -f re-natal ]; then
  ln -s ./node_modules/re-natal/index.js re-natal
else
  echo "re-natal exists"
fi
