package com.topfootballtips.topfootballtip.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.topfootballtips.topfootballtip.R;
import com.topfootballtips.topfootballtip.api.api_models.TipDatum;
import com.topfootballtips.topfootballtip.constants.CountryName;
import com.topfootballtips.topfootballtip.constants.Preferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TipAdapter extends ArrayAdapter<TipDatum> implements View.OnClickListener {
    private Context mContext;
    private int mResource;

    public TipAdapter(Context context, int elementResource, List<TipDatum> tips) {
        super(context, elementResource,tips);
        this.mContext = context;
        this.mResource = elementResource;
    }

    private static class ViewHolderItem {
        ImageView countyImage;
        ImageButton bettingSiteButton;
        TextView county;
        TextView match;
        TextView matchResult;
        TextView matchDate;
        TextView coef;
        TextView tip;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;

        if(convertView == null){

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mResource, parent, false);

            viewHolder = new ViewHolderItem();
            viewHolder.countyImage = (ImageView) convertView.findViewById(R.id.img_country_flag);
            viewHolder.bettingSiteButton = (ImageButton) convertView.findViewById(R.id.btn_img_bet_site);
            viewHolder.county = (TextView) convertView.findViewById(R.id.tw_country);
            viewHolder.match = (TextView) convertView.findViewById(R.id.tw_match);
            viewHolder.matchResult = (TextView) convertView.findViewById(R.id.tw_result);
            viewHolder.matchDate = (TextView) convertView.findViewById(R.id.tw_match_date);
            viewHolder.coef = (TextView) convertView.findViewById(R.id.tw_coef);
            viewHolder.tip = (TextView) convertView.findViewById(R.id.tw_tip);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        // object item based on the position
        TipDatum tip = getItem(position);

        // assign values if the object is not null
        if(tip != null) {
            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            String countryText = tip.getCountry().trim();
            this.setImageTo(viewHolder.countyImage, countryText);
            viewHolder.bettingSiteButton.setImageResource(R.drawable.ic_btn_betting_site);
            viewHolder.bettingSiteButton.setOnClickListener(this);
            viewHolder.county.setText(countryText);
            viewHolder.match.setText(tip.getMatch());
            viewHolder.matchResult.setText(tip.getResult());

            SimpleDateFormat dateFormat = new SimpleDateFormat(Preferences.DATE_FORMAT, Locale.getDefault());
            try {
                viewHolder.matchDate.setText(dateFormat.format(new Date(tip.getMatchDate())));
            } catch (NumberFormatException e){
                e.printStackTrace();
            }

            viewHolder.coef.setText(String.format(Locale.getDefault(),"%.2f", tip.getCoef()));
            viewHolder.tip.setText(tip.getTip());

            /*if (tip.getTipType() == Preferences.TIP_TYPE_NORMAL){
                viewHolder.tip.setText(tip.getTip()); //normal tip text
            } else if (tip.getTipType() == Preferences.TIP_TYPE_VIP){
                viewHolder.tip.setText(String.format(Locale.getDefault(),"IMPLEMENT TIP %1$s : %2$s",Preferences.TIP_TYPE_VIP,tip.getTip()));
            }*/

            int tipHit = 0;
            try {
                tipHit = tip.getTipResult();
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

            if(tipHit == 0) {
                viewHolder.tip.setBackgroundResource(R.drawable.shape_neutral_guessed_result);
            }else if(tipHit == 1){
                viewHolder.tip.setBackgroundResource(R.drawable.shape_guessed_result);
            }else if(tipHit == 2){
                viewHolder.tip.setBackgroundResource(R.drawable.shape_not_guessed_result);
            }
        }

        return convertView;
    }

    private void setImageTo(ImageView county_img, String countryText) {
        if (countryText.equalsIgnoreCase(CountryName.ARGENTINA)){
            county_img.setImageResource(R.drawable.ic_argentina);
        }else if(countryText.equalsIgnoreCase(CountryName.AUSTRALIA)){
            county_img.setImageResource(R.drawable.ic_australia);
        }else if(countryText.equalsIgnoreCase(CountryName.BRAZIL)){
            county_img.setImageResource(R.drawable.ic_brazil);
        }else if(countryText.equalsIgnoreCase(CountryName.BULGARIA)){
            county_img.setImageResource(R.drawable.ic_bulgaria);
        } else if(countryText.equalsIgnoreCase(CountryName.CHAMPIONS_LEAGUE)){
            county_img.setImageResource(R.drawable.ic_champions_league2);
        } else if(countryText.equalsIgnoreCase(CountryName.DENMARK)){
            county_img.setImageResource(R.drawable.ic_denmark);
        }else if(countryText.equalsIgnoreCase(CountryName.ENGLAND)){
            county_img.setImageResource(R.drawable.ic_england);
        } else if(countryText.equalsIgnoreCase(CountryName.EUROPA_LEAGUE)){
            county_img.setImageResource(R.drawable.ic_europaleague);
        } else if(countryText.equalsIgnoreCase(CountryName.FRANCE)){
            county_img.setImageResource(R.drawable.ic_france);
        } else if(countryText.equalsIgnoreCase(CountryName.GERMANY)){
            county_img.setImageResource(R.drawable.ic_germany);
        } else if(countryText.equalsIgnoreCase(CountryName.GREECE)){
            county_img.setImageResource(R.drawable.ic_greece);
        } else if(countryText.equalsIgnoreCase(CountryName.ITALY)){
            county_img.setImageResource(R.drawable.ic_italy);
        } else if(countryText.equalsIgnoreCase(CountryName.NETHERLANDS)){
            county_img.setImageResource(R.drawable.ic_netherlands);
        } else if(countryText.equalsIgnoreCase(CountryName.PORTUGAL)){
            county_img.setImageResource(R.drawable.ic_portugal);
        } else if(countryText.equalsIgnoreCase(CountryName.RUSSIA)){
            county_img.setImageResource(R.drawable.ic_russia);
        } else if(countryText.equalsIgnoreCase(CountryName.SERBIA)){
            county_img.setImageResource(R.drawable.ic_serbia);
        } else if(countryText.equalsIgnoreCase(CountryName.SLOVAKIA)){
            county_img.setImageResource(R.drawable.ic_slovakia);
        } else if(countryText.equalsIgnoreCase(CountryName.SLOVENIA)){
            county_img.setImageResource(R.drawable.ic_slovenia);
        } else if(countryText.equalsIgnoreCase(CountryName.SPAIN)){
            county_img.setImageResource(R.drawable.ic_spain);
        } else if(countryText.equalsIgnoreCase(CountryName.SWITZERLAND)){
            county_img.setImageResource(R.drawable.ic_switzerland);
        } else if(countryText.equalsIgnoreCase(CountryName.USA)){
            county_img.setImageResource(R.drawable.ic_united_states);
        } else {
            county_img.setImageResource(R.drawable.ic_default_img);
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        Context context = view.getContext();
        if (id == R.id.btn_img_bet_site){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Preferences.BETTING_SITE_URL));
            if (browserIntent.resolveActivity(context.getPackageManager()) != null) {
                view.getContext().startActivity(browserIntent);
            }else{
                Toast.makeText(context, R.string.no_browser_text, Toast.LENGTH_LONG).show();
            }
            return;
        }
    }
}

