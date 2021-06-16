/* eslint-disable react-native/no-inline-styles */
import React from 'react';
import {Text, View, StyleSheet, Image} from 'react-native';

const ItemRows = ({item}) => {
  return (
    <View style={styles.rows}>
      <View
        style={{
          flexDirection: 'row',
          justifyContent: 'space-around',
        }}>
        <View>
          <Image
            source={{
              uri: `https://www.countryflags.io/${item.CountryCode}/flat/64.png`,
            }}
            style={styles.flag}
          />
        </View>
        <View style={{marginRight: 100, marginTop: 5}}>
          <Text style={styles.countryName}>{item.Country}</Text>
        </View>
        <View>
          <Text style={styles.totalCases}>{item.TotalConfirmed}</Text>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  rows: {
    width: '95%',
    marginTop: 10,
    marginBottom: 8,
    marginLeft: 8,
    padding: 10,
    backgroundColor: '#37474f',
    borderRadius: 10,
  },
  countryName: {
    fontSize: 13,
    color: '#fff',
    fontWeight: 'bold',
  },
  totalCases: {
    fontSize: 12,
    color: '#fff',
    fontWeight: 'bold',
    marginTop: 5,
  },
  flag: {
    height: 30,
    width: 40,
    padding: 10,
    borderRadius: 1000,
  },
});

export default ItemRows;
