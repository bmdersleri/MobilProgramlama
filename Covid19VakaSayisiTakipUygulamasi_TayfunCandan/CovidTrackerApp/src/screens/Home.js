/* eslint-disable no-trailing-spaces */
/* eslint-disable prettier/prettier */
/* eslint-disable no-unused-vars */
/* eslint-disable react-native/no-inline-styles */
import React, {useEffect, useState} from 'react';
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  TouchableOpacity,
  Alert,
} from 'react-native';
import {ScrollView} from 'react-native-gesture-handler';
import Icon from 'react-native-ionicons';
import Cards from '../components/Cards';
import ItemRows from '../components/ItemRows';


const Home = () => {
  const url = 'https://api.covid19api.com/summary';
  const [data, setData] = useState();
  const [isLoading, setIsloading] = useState(false);

  useEffect(() => {
    const fetchCovidData = async () => {
      setIsloading(true);
      try {
        const result = await fetch(url);
        const response = await result.json();
        setData(response);
        setIsloading(false);
      } catch (e) {
        console.log(e);
      }
    };
    fetchCovidData();
  }, 
  // Gelen veride herhangi bir değişiklik olduğunda güncellenmesi için [] faydalanılır.
  []);

  return (
    <View style={styles.container}>
      <Text style={styles.covidHeading}>COVID 19 STATUS</Text>
      <TouchableOpacity
        onPress={() => {
          Alert.alert('Hazırlayan', 'Tayfun CANDAN');
        }}
        style={styles.info}>
        <Icon name={'information-circle-outline'} size={30} color={'white'} />
      </TouchableOpacity>

      <View style={styles.cards}>
        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
          style={{marginTop: 100}}>
          <Cards
            icon="pulse"
            title="Total Cases"
            bg="#FFF"
            number={data ? data.Global.TotalConfirmed : 0}
          />

          <Cards
            icon="medkit"
            title="Recovered"
            bg="#FFF"
            number={data ? data.Global.TotalRecovered : 0}
          />

          <Cards
            icon="nuclear"
            title="Death Reported"
            bg="#FFF"
            number={data ? data.Global.TotalDeaths : 0}
          />
        </ScrollView>
      </View>
      <View>
        <View
          style={{
            flexDirection: 'row',
            justifyContent: 'center',
            alignItems: 'center',
          }}>
          <Text style={styles.casesHeading}>Total Covid Cases by Country</Text>
        </View>
      </View>
      <View style={styles.flatList}>
        <FlatList
          data={data && data.Countries ? data.Countries : 0}
          renderItem={({item}) => <ItemRows item={item} />}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#102027',
  },
  info: {
    position: 'absolute',
    right: 15,
    top: 15,
  },
  covidHeading: {
    color: '#FFF',
    fontSize: 20,
    alignSelf: 'center',
    fontWeight: 'bold',
    marginTop: 50,
  },
  cards: {
    marginTop: -90,
  },
  casesHeading: {
    color: '#FFF',
    fontSize: 15,
    alignSelf: 'center',
    fontWeight: 'bold',
    marginTop: 30,
  },
  flatList: {
    marginTop: 10,
  },
});

export default Home;
